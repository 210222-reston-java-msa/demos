import { CatService } from './../services/cat.service';
import { Component, OnInit } from '@angular/core';
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
  // @Input() cat?: Cat; -----> this is what we originally had 

  // This property represents the cat whose details are being displayed
  cat?: Cat;
  // the ? mark here represents a safe navigation operator -- it is protecting us incase the value is undefined

  allCats: Cat[] = [];

  constructor(
    private catService: CatService,
    private route: ActivatedRoute,
    private location: Location) { }

  ngOnInit(): void {

    // when the component is initialized, we'll need to set the cat property...
    this.getCat();

  }


  // this method will extract the cat by grabbing the id from the parameter
  // then calling the catService and FINDING that cat within the "mock DB"

  // Shout out to all the Quinns -- must make sure that the service method is returning type <any>
  getCat(): void {

    // we are extracting the id from the /detail/:id
    const id = Number(this.route.snapshot.paramMap.get('id')); // this complicated line grabs the ID part of the /detail/:id

    // then we
    this.catService.getCat(id).subscribe(cat => this.cat = cat);
  }



  /*
      save() Method which calls on an updateCat method in the service (we will then build the updateCat method
        in the service...)
  */
  save(): void {
    this.catService.updateCat(this.cat).subscribe(() => this.goBack());
    console.log(`The cat is ${this.cat?.name}`)
  }

  goBack() {
    // we'll use the location object
    this.location.back();
  }



}
