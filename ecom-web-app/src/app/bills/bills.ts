import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-bills',
  templateUrl: './bills.html',
  styleUrls: ['./bills.css'],
  standalone: false
})
export class Bills implements OnInit {
  public bills: any = null;
  public customers: any = null;

  private billsUrl = "http://localhost:8888/BILLING-SERVICE/api/bills";
  private customersUrl = "http://localhost:8888/ECOM-II-BDCC-APP/api/customers";

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<any>(this.billsUrl).subscribe({
      next: (data) => {
        this.bills = data;
        this.loadCustomers();
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

  loadCustomers(): void {
    this.http.get<any>(this.customersUrl).subscribe({
      next: (data) => {
        this.customers = data;
      },
      error: (err) => {
        console.error("Erreur chargement clients:", err);
      }
    });
  }

  getCustomerName(customerId: number): string {
    if (!this.customers?._embedded?.customers) return '';

    const customer = this.customers._embedded.customers.find(
        (c: any) => c.id === customerId
    );
    return customer ? customer.name : 'Client inconnu';
  }

  formatDate(dateString: string): string {
    const date = new Date(dateString);
    return date.toLocaleDateString('fr-FR') + ' ' + date.toLocaleTimeString('fr-FR');
  }

  viewBillDetails(bill: any): void {
    console.log("Bill links:", bill._links);

    if (!bill._links.productItems) {
      alert("Link productItems non trouvé");
      return;
    }

    const originalUrl = bill._links.productItems.href;
    console.log("Original URL from JSON:", originalUrl);

    const urlParts = originalUrl.split('/api/');
    if (urlParts.length < 2) {
      alert("URL invalide");
      return;
    }

    const productItemsUrl = `http://localhost:8888/BILLING-SERVICE/api/${urlParts[1]}`;
    console.log("Dynamic URL:", productItemsUrl);

    this.http.get<any>(productItemsUrl).subscribe({
      next: (productItemsData) => {
        console.log("Product Items Data:", productItemsData);

        let message = 'DETAILS DE LA FACTURE\n';
        message += '----------------------\n';
        message += 'Numero: ' + bill._links.self.href.split('/').pop() + '\n';
        message += 'Client: ' + this.getCustomerName(bill.customerId) + '\n';
        message += 'Date: ' + this.formatDate(bill.billingDate) + '\n\n';

        message += 'PRODUITS ACHETES:\n';
        message += '-----------------\n';

        let total = 0;

        if (productItemsData._embedded?.productItems?.length > 0) {
          productItemsData._embedded.productItems.forEach((item: any, index: number) => {
            const productTotal = item.unitPrice * item.quantity;
            total += productTotal;

            message += (index + 1) + '. Produit ID: ' + item.productId + '\n';
            message += '   Quantite: ' + item.quantity + ' x ' + item.unitPrice + ' EUR\n';
            message += '   Sous-total: ' + productTotal + ' EUR\n\n';
          });

          message += '----------------------\n';
          message += 'TOTAL: ' + total + ' EUR\n';
        } else {
          message += 'Aucun produit dans cette facture\n';
        }

        message += '======================';

        alert(message);
      },
      error: (err) => {
        console.error("Error:", err);

        // Version simple
        let message = 'DETAILS DE LA FACTURE\n';
        message += '----------------------\n';
        message += 'Numero: ' + bill._links.self.href.split('/').pop() + '\n';
        message += 'Client: ' + this.getCustomerName(bill.customerId) + '\n';
        message += 'Date: ' + this.formatDate(bill.billingDate) + '\n';
        message += '----------------------\n';
        message += 'Service Billing: Opérationnel';

        alert(message);
      }
    });
  }
}
