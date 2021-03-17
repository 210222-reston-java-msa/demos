import { CatService } from './../services/cat.service';
import { Cat } from './../cat';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  topCats: Cat[] = [];

  // I need to inject a dependency which fetches data for me
  constructor(private catService: CatService) { }

  // whenever the component is loaded/initialized, getTopCats will be called and we have
  // an array of the top 4 cats, which will be displayed according to our html template
  ngOnInit(): void {
    this.getTopCats();

  }


  // this method will set our topCats property to the top 4 cats on the list of all cats
  getTopCats(): void {
    this.catService.getCats().subscribe(allCats => this.topCats = allCats.slice(0, 4))
    // allCats represents all of the data returned from the getCats() method in the CatService.
    // we use the slice() method which will return the first 4 elements within the array of data
  }
  
}
