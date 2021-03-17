import { MessageService } from './../services/message.service';
import { CatService } from './../services/cat.service';
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
  allCats: Cat[] = [];

  // the ? mark allows us to leave this property as undefined
  selectedCat?: Cat;
  // the following works too
  // selectedCat: any;

  // This is dependency injection (we are injecting an instance of the service into
  // the component class)
  constructor(private catService: CatService, private messageService: MessageService) { }

  // ngOnInit is a lifeCycle Hook, which means that WHEN the component is loaded and initalized, some methods will be called
  ngOnInit(): void {
    this.getAllCats();
  }

  // This method will be expressed in our html template (cat.component.html)
  // when we select a cat, it get's assigned as a specific property (selectedCat? property )
  onSelect(cat: Cat): void {
    this.selectedCat = cat;

    // every time we click on a Cat Item,  we add to the messages property inside the MessageService.
    // ----> It will say "You click on a Cat with Id of ${cat.id} and name of ${cat.name}"

    this.messageService.add(`CatsComponent: You Selected cat ${cat.name} with the id: ${cat.id}`)

  }

  // this method will retreive all the cats by using the Service that we've injected into this component
  // and set the allCats property

  // Now we have to handle the return of an ASYHNCRONOUS Operatation by subscribing to it
  getAllCats(): void {
    this.catService.getCats().subscribe(retrievedCats => this.allCats = retrievedCats);
  }


  // Currently this is a SYNCHROUNOUS Method signature
  // getAllCats(): void {
  //   this.allCats = this.catService.getCats();
  // }
}
