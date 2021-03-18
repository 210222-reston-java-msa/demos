import { CatService } from './../services/cat.service';
import { Cat } from './../cat';
import { Component, OnInit } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';


@Component({
  selector: 'app-cat-search',
  templateUrl: './cat-search.component.html',
  styleUrls: ['./cat-search.component.css']
})
export class CatSearchComponent implements OnInit {

  cats$?: Observable<Cat[]>;
  private searchTerms = new Subject<string>();
  // A Subject comes from RXJS --> it is both a source of obseravble values and an Observable itself
  // you can SUBSCRIBE to a Subject

  constructor(private catService: CatService) { }

  // we take in a term and push it into an obseravble stream
  search(term: string): void {
    this.searchTerms.next(term);
  }

  
  ngOnInit(): void {

    // 1. set our cats$ obserable = to our serach terms
    this.cats$ = this.searchTerms.pipe(

      // wait 300 ms after each keystroike before considering the term
      debounceTime(300),

      // this method ignores a term if it's the SAME as our previously entered term
      distinctUntilChanged(),

      // switch to a new search observable each time the term changes
      switchMap((term: string) => this.catService.searchCats(term)),
    );

  }

}
