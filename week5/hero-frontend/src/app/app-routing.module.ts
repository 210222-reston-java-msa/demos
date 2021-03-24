import { RegisterComponent } from './components/register/register.component';
import { FindComponent } from './components/find/find.component';
import { AllComponent } from './components/all/all.component';
import { MainComponent } from './components/main/main.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [

  { path: '', redirectTo: 'main', pathMatch: 'full' },
  { path: 'main', component: MainComponent },
  { path: 'all', component: AllComponent },
  { path: 'find', component: FindComponent },
  { path: 'register', component: RegisterComponent }
  // the path value is correlated to your routerLink...the only difference is a "/"

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
