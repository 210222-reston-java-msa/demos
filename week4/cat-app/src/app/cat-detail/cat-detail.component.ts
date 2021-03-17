import { CatService } from './../services/cat.service';
import { Component, OnInit} from '@angular/core';
import { Cat } from '../cat';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-cat-detail',
  templateUrl: './cat-detail.component.html',
  styleUrls: ['./cat-detail.component.css']
})
export class CatDetailComponent implements OnInit {

  // we use the @Input decorator to specify that this particular property 
  // can be input from another source.
  // @Input() cat?: Cat;

  // When we come back after lunch (3pm)
  cat: Cat;


  constructor(
    
    private CatService: CatService,
    private route: ActivatedRoute,
    private location: Location) { }

  ngOnInit(): void {
      
    // when the component is initialized, we'll need to set the cat proeprty...
    this.getCat();
  }


  // this method will extract the cat by grabbing the id from the parameter
  // then calling the catService and FINDING that cat within the "mock DB"
  getCat(): void {
    
    this.cat = 
      // here we'll call our CatService getCats method and then do something with the Cat we get back
  }

  goBack() {
    // we'll use the location
  }

}
