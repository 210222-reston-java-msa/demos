import { CatDetailComponent } from './cat-detail/cat-detail.component';

import { DashboardComponent } from './dashboard/dashboard.component';
import { CatsComponent } from './cats/cats.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


// just like a front controller
const routes: Routes = [

  { path: 'everySingleCat', component: CatsComponent }, // localhost:4200/everySingleCat --> display my CatsComponent
  { path: 'dashboard', component: DashboardComponent },

  // the colon represents a placeholder for a NUMBER that will be injected later
  { path: 'detail/:id',  component: CatDetailComponent },

  // Im going to make Dashboard my default route
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },

  
  // this is a wildcard path that allows us to catch garbage endpoint
  { path: '**', redirectTo: '/dashboard' }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule] // the exports keyowrd makes this RouterModule public and accessible thorughout the app!
})
export class AppRoutingModule { }
