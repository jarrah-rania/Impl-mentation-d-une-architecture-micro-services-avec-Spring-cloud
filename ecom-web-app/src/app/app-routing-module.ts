import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {Products} from './products/products';
import {Customers} from './customers/customers';
import {Bills} from './bills/bills';

const routes: Routes = [
  {
    path: "products" ,component : Products

  },
  {
    path :"customers",component :Customers
  },
  { path: "bills", component: Bills }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
