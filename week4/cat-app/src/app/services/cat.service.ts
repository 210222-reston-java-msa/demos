import { MessageService } from './message.service';
import { LoggerService } from './logger.service';
import { CATS } from './../mock-cats';
import { Injectable } from '@angular/core';
import { Cat } from '../cat';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';


// This Injectable Decorator (anything with an @ sign is a decorator)
// means that this service and it s functionality can be passed to, or INJECTED 
// into a separate component, so that the component can use its service.
@Injectable({
  providedIn: 'root'
})
export class CatService {

  // We are defining a catsUrl in the form of :base/:collectionName
  private catsUrl = 'api/allOfTheCats'; // URL to web api --> "allOfTheCats refers to the name 
  // of what we're returning in our in-memory-data.service.ts"

  constructor(
    private logger: LoggerService,
    private messageService: MessageService,
    private http: HttpClient) { }


  private log(message: string) {
    this.messageService.add(`CatService: ${message}`)
  }

  // =========== WITH HTTP ==========================
  // ==== CRUD --> READ (all)
  /* GET cats from the server*/
  getCats(): Observable<Cat[]> {
    return this.http.get<Cat[]>(this.catsUrl)
      .pipe(
        // tap allows us to DO something with the values we receive without changing them
        tap(_ => this.log(`fetched cats!`)),
        // if there's an error in recieving data, we recover gracefully by returning an empty array
        catchError(this.handleError<Cat[]>('getCats', []))
      )
  }

  // =========== READ ---> READ (by id)
  // Get Cat by ID! We are tackling a TODO....
  /**GET a cat by its id.  Will return a 404 if not found */
  getCat(id: number): Observable<Cat> {

    // grab the entire collection of Cats...then add a / and the id of the cat that was passed in
    const specificUrl = `${this.catsUrl}/${id}`;
    return this.http.get<Cat>(specificUrl)
      .pipe(
        tap(_ => this.log(`fetchedcat with id = ${id}`)),

        catchError(this.handleError<Cat>(`getCat id=${id}`))
      );
  }

  // 1. create a save button in the html template cat-detail component -- CHECK 

  // 2. create a method in the cat-detail.ts file which calls on a udpate method in the cat-service - CHECK 

  // 3. Create an update method in the service -- WHAT WE'RE DOING NOW

  //**PUT method to update a resource in DB  */
  updateCat(cat?: Cat): Observable<any> {
    // We are updating our resource (all the cats held in our server) with the cat object we pass through to add.
    return this.http.put(this.catsUrl, cat)
      .pipe(
        tap(_ => this.log(`updated cat id=${cat?.id}`)), // this isn't totally necessary, but good for logging pruposes and error handling 
        catchError(this.handleError<any>('updateCat'))
      );
  }

  /*
      POST METHOD
  */
  addCat(cat: Cat): Observable<any> {
    return this.http.post(this.catsUrl, cat) // this is the only part of the method we want to focus on.
      .pipe(
        tap(_ => this.log(`added cat id=${cat?.id}`)), // this isn't totally necessary, but good for logging pruposes and error handling 
        catchError(this.handleError<any>('addCat'))
      );
  }


  /*
      DELETE METHOD --> HTTP DELETE
  */
  deleteCat(cat: Cat | number): Observable<Cat> {

    // here we are checking did we pass in a number (id)? or a Cat?
    const id = (typeof cat === 'number') ? cat : cat.id;

    // console.log(typeof 14); // this would return the STRING 'number'

    const url = `${this.catsUrl}/${id}`;

    return this.http.delete<Cat>(url)
      .pipe(
        tap(_ => this.log(`deleted cat id=${id}`)), // this isn't totally necessary, but good for logging pruposes and error handling 
        catchError(this.handleError<any>('deleteCat'))
      );

  }

  /*

      SEARCH CATS! --> GET cats whose name contains search term
  */

  searchCats(term: string): Observable<Cat[]> {

        if(!term.trim()) {
          // if no search term exists, we send back an empty array as an observable
          return of([]);
        }

        return this.http.get<Cat[]>(`${this.catsUrl}/?name=${term}`);
  }




  // TODO: transform this to a method that utilizes HttpClient  Service
  // getCat(id: number): Observable<Cat | undefined> {

  //   this.messageService.add(`CatService: fetched cat with id ${id}`);

  //   return of(CATS.find(cat => cat.id === id));

  // }


  /*
    Handle an HTTP operation that fails with a customer error handler 
    * We do this to let the app continue!

    * @param operation - name of the operation that fails
    * @param result - optional value that is returned as the OBSERVABLE result 
  */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to a remote logging infrastructure
      this.logger.error("WE ENCOUNTERED AN ERROR IN " + operation);
      console.error(error) // we'll just log it to the console

      // TODO: better job transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // we want to ensure that the app keeps running by returning an empty result
      return of(result as T);
    }
  }







  // ========== before HTTP =================================
  // This is the OLD asynchronous signature BEFORE HTTP
  //getCats(): Observable<Cat[]> {

  //   const cats = of(CATS) // What we pass to the of() method is our data source. // imagine as if it were http://localhost:8080/EmployeeDBServlets/allcats
  //   // we return an observable called cats

  //   // Let's log how many cats we fetch ( we log the size of array fetched  )
  //   // Subscribe takes a callback...this is the function that we apply to the data we fetch from the Observable
  //   cats.subscribe(catArray => {
  //     this.logger.log(`We returned ${catArray.length} cats iside the CatService.`);
  //   });

  //   this.messageService.add("in CatService: cats have been fetched for sure...");

  //   return cats;
  // }

  // This is the old synchronous way
  // getCats(): Cat[] {
  //   return CATS;
  // }

}
