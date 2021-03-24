import { ClientMessage } from './../models/client-message.model';
import { Hero } from './../models/hero.model';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class HeroService {

  constructor() { }


  public registerHero() {

  }

  public findHero() {

  }

  public findAllHeroes() {
    
  }

}
