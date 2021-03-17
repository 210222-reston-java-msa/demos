import { MessageService } from './message.service';
import { LoggerService } from './logger.service';
import { CATS } from './../mock-cats';
import { Injectable } from '@angular/core';
import { Cat } from '../cat';
import { Observable, of } from 'rxjs';

// This Injectable Decorator (anything with an @ sign is a decorator)
// means that this service and it s functionality can be passed to, or INJECTED 
// into a separate component, so that the component can use its service.
@Injectable({
  providedIn: 'root'
})
export class CatService {

  constructor(private logger: LoggerService, private messageService: MessageService) { }


  // This is now an asynchronous signature
  getCats(): Observable<Cat[]> {

    const cats = of(CATS) // What we pass to the of() method is our data source. // imagine as if it were http://localhost:8080/EmployeeDBServlets/allcats
    // we return an observable called cats

    // Let's log how many cats we fetch ( we log the size of array fetched  )
    // Subscribe takes a callback...this is the function that we apply to the data we fetch from the Observable
    cats.subscribe(catArray => {
      this.logger.log(`We returned ${catArray.length} cats iside the CatService.`);
    });


    this.messageService.add("in CatService: cats have been fetched for sure...");

    return cats;
  }

  // This is the old synchronous way
  // getCats(): Cat[] {
  //   return CATS;
  // }


  // We use this method in the cat-detail.component.ts file......
  // because we need to get the details of 1 individual Cat
  getCat(id: number): Observable<Cat | undefined> {

    this.messageService.add(`CatService: fetched cat with id ${id}`);

    return of(CATS.find(cat => cat.id === id));

  }

}
