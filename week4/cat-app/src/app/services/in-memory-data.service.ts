import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';
import { Cat } from '../cat';

// This file will take over the funcitonalioty of the mock-cats.ts file.....
@Injectable({
  providedIn: 'root'
})
export class InMemoryDataService implements InMemoryDbService {

  constructor() { }

  // we are imagining that this is a DB in some RDS somewhere like in PostGresQL
  createDb() {

    const allOfTheCats = [

      { id: 11, name: 'Whiskers' },
      { id: 12, name: 'Spot' },
      { id: 13, name: 'Mr. Fluff' },
      { id: 14, name: 'Finn' },
      { id: 15, name: 'Caesar' },
      { id: 16, name: 'Brutus' },
      { id: 17, name: 'Artemis' },
      { id: 18, name: 'Nico' },
      { id: 19, name: 'Cecil' },
      { id: 20, name: 'Clancy' }

    ];

    // this is what's considered our "collectionName"
    return {allOfTheCats};
  }


  // We are creating this method so that a cat ALWAYS has an id.
  // IF the cats array is empty, the method below returns the initial number (11).
  // If the array is NOT empty,, the method returns the higest id + 1
  genId(cats: Cat[]): number {

    // this is a ternary operator ( a shorthand way of writing an if/else statement)
    return cats.length > 0 ? Math.max(...cats.map(cat => cat.id)) + 1 : 11;

  }
}
