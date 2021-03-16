import { Component, OnInit, Input } from '@angular/core';
import { Cat } from '../cat';

@Component({
  selector: 'app-cat-detail',
  templateUrl: './cat-detail.component.html',
  styleUrls: ['./cat-detail.component.css']
})
export class CatDetailComponent implements OnInit {

  // we use the @Input decorator to specify that this particular property 
  // can be input from another source.
  @Input() cat?: Cat;

  constructor() { }

  ngOnInit(): void {
  }

}
