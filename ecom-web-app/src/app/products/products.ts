import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-products',
  standalone: false,
  templateUrl: './products.html',
  styleUrl: './products.css',
})
export class Products implements OnInit {
  public products: any = null;
  private apiUrl = "http://localhost:8888/IVENTORY-SERVICE/api/products";

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<any>(this.apiUrl).subscribe({
      next: (data) => {
        console.log("DATA = ", data);
        console.log("Embedded products = ", data._embedded?.products);
        this.products = data;
      },
      error: (err) => {
        console.error(err);
      }
    });
  }
}
