import { HERO_URL } from './../../environments/environment';
import { ClientMessage } from './../models/client-message.model';
import { Hero } from './../models/hero.model';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
// Remember to add HTTPClient to your imports[] in your app.module.ts
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class HeroService {

  httpOptions = {
    // Shout out to Farid for figuring out that Content-Type must have a "-" if used
    headers: new HttpHeaders({'Content-Type' : 'application/json'})
  }

  constructor(private http: HttpClient) { }

  /*
  * POST
   */
  public registerHero(hero: Hero): Observable<ClientMessage>  {
    // this will return a client message if we are successfully able to POST a hero to our server 
    return this.http.post<ClientMessage>(`${HERO_URL}register`, hero, this.httpOptions)
                                                                      // adding httpOptions just constructs a more robust
                                                                      // post request to our server.  We are asking our
                                                                      // server to return it as JSON.
    .pipe(
      catchError(this.handleError<any>('cannot register hero!'))
    )
  }

  /*
  * POST
  */
  public findHero(hero: Hero): Observable<Hero> {

    return this.http.post<Hero>(`${HERO_URL}findHero`, hero, this.httpOptions)

    .pipe(
      catchError(this.handleError<Hero>('getHero', undefined))
    )
  }

  /*
  * HTTP GET
  */
  public findAllHeroes(): Observable<Hero[]> {

    return this.http.get<Hero[]>(`${HERO_URL}findAllHeroes`) // at the http://locahost:8080/SpringMVC/findAllHeroes we have the capability the
                                                            // SERVICE to deliver you an array of Heroes (in JSON)
    // remember that the .pipe(catchError...) is only necessary for handling errors tht could occur
    .pipe(
      catchError(this.handleError<Hero[]>('getHeroes', []))
    )

  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log it to the console if something goes wrong

      // Let the app keep running by returning an empty result.
      return of(result as T);
    }
  }

}
