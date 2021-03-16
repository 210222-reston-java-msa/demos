import { Component, OnInit } from '@angular/core';
import { Cat } from '../cat';
import { CATS } from '../mock-cats';

// The Component decorator labels this class as a Component and its metadata 
@Component({
  selector: 'app-cats',
  templateUrl: './cats.component.html',
  styleUrls: ['./cats.component.css']
})
export class CatsComponent implements OnInit {

  // this is a property of the component
  allCats = CATS;

  // the ? mark allows us to leave this property as undefined
  selectedCat?: Cat;
  // the following works too
  // selectedCat: any;

  constructor() { }

  ngOnInit(): void {
  }

  // This method will be expressed in our html template (cat.component.html)
  // when we select a cat, it get's assigned as a specific property (selectedCat? property )
  onSelect(cat: Cat): void {
    this.selectedCat = cat;
  }

}
