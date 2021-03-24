import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainComponent } from './components/main/main.component';
import { NavComponent } from './components/nav/nav.component';
import { FindComponent } from './components/find/find.component';
import { AllComponent } from './components/all/all.component';
import { RegisterComponent } from './components/register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    NavComponent,
    FindComponent,
    AllComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule, // very important that you IMPORT you import HttpClientModule
    FormsModule // we need to import this if we want to use 2 way data binding [(ngModel)]
  ],
  providers: [],
  // boostrap means that it's self starting
  bootstrap: [AppComponent]
})
export class AppModule { }
