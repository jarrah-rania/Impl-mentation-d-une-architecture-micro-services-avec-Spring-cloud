import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {Products} from './products/products';

export const routes: Routes = [
  {path : "products",component : ProductsComponents
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
