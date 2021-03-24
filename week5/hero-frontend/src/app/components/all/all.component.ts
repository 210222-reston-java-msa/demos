import { ClientMessage } from './../../models/client-message.model';
import { Hero } from './../../models/hero.model';
import { HeroService } from './../../services/hero.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-all',
  templateUrl: './all.component.html',
  styleUrls: ['./all.component.css']
})
export class AllComponent implements OnInit {

  title = 'All Heroes';
  public heroes: Hero[] = [];
  public clientMessage: ClientMessage = new ClientMessage('Sorry, no heroes to display');

  constructor(private heroService: HeroService) { }

  ngOnInit(): void {
      // we will set the heroes array = to all of the heroes fetched from the server
    this.findAllHeroesFromService();
  }

  public findAllHeroesFromService(): void {
    // in this method we call on our service to fetch the heroes array and set it equal to 
    // our heroes property
    this.heroService.findAllHeroes().subscribe(data => this.heroes = data)
  }

}
