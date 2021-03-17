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

  // When we come back after lunch (3pm)
  cat?: Cat;
  // the ? mark here represents a safe navigation operator -- it is protecting us

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

    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.catService.getCat(id).subscribe(cat => this.cat = cat);
  }


  // Xing's solution to the above method -- no need
  xingGetCat(): void {
    this.route.params.subscribe(params => {
      console.log(params)

      // params id
      let id: string = params.id;

      // all cats
      this.catService.getCats().subscribe(data => {
        this.allCats = data;

        for (let i: number = 0; i < this.allCats.length; i++) {
          if (this.allCats[i].id == Number(id)) {

            this.cat = this.allCats[i];
          }
        }
      });
    })
  }


  goBack() {
    // we'll use the location
    this.location.back();
  }



}
