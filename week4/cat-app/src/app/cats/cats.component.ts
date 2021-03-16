import { Component, OnInit } from '@angular/core';
import { Cat } from '../cat';

// The Component decorator labels this class as a Component and its metadata 
@Component({
  selector: 'app-cats',
  templateUrl: './cats.component.html',
  styleUrls: ['./cats.component.css']
})
export class CatsComponent implements OnInit {

  // this is a property of the component
  cat: Cat = {
    id: 1,
    name: 'Winston'
  };

  constructor() { }

  ngOnInit(): void {
  }

}
