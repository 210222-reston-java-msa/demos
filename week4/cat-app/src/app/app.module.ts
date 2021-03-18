import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CatsComponent } from './cats/cats.component';
import { FormsModule } from '@angular/forms';
import { CatDetailComponent } from './cat-detail/cat-detail.component';
import { MessagesComponent } from './messages/messages.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HttpClientModule } from '@angular/common/http';
import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api';
// this is a custom service that we generate with the command: ng generate serivce services/InMemoryData
import { InMemoryDataService } from './services/in-memory-data.service';
import { CatSearchComponent } from './cat-search/cat-search.component';

// The AppModule stores metadata about our entire application
// For example, if we want to use the 2 way property binding feature of the FormsModule,
// we need to import it in our AppModule
@NgModule({
  declarations: [
    AppComponent,
    CatsComponent,
    CatDetailComponent,
    MessagesComponent,
    DashboardComponent,
    CatSearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,


    // The HttpClientInMemoryWebApiModule is intercepting our HTTP requests
    // and returning simulated server responses.
    // We will REMOVE THIS when we are ready to recieve REAL requests
    HttpClientInMemoryWebApiModule.forRoot(
      InMemoryDataService, { dataEncapsulation: false}
      )
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
