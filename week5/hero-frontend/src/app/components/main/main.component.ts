import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent {

  title = 'Super Hero Portal';
  image = 'assets/superhero.jpg'; // This is pointing to the location of my superhero.jpg image

}