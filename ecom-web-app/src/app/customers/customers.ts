import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-customers',
  standalone: false,
  templateUrl: './customers.html',
  styleUrl: './customers.css',
})
export class Customers implements OnInit{
  customers:any;
  private apiUrl = "http://localhost:8888/ECOM-II-BDCC-APP/api/customers";

  constructor(private http:HttpClient) {
  }
  ngOnInit(): void { this.http.get<any>(this.apiUrl).subscribe({
    next: (data) => {
      console.log("DATA = ", data);
      console.log("Embedded products = ", data._embedded?.products);
      this.customers = data;
    },
    error: (err) => {
      console.error(err);
    }
  });
  }

}
