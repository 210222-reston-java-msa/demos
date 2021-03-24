import { ClientMessage } from './../../models/client-message.model';
import { Hero } from './../../models/hero.model';
import { HeroService } from './../../services/hero.service';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-find',
  templateUrl: './find.component.html',
  styleUrls: ['./find.component.css']
})
export class FindComponent {

  title = 'Find Hero';

  // Capture user input -- we will have an empty hero object like in register.component.ts
  public hero: Hero = new Hero(0, '', '', false)

  // Present the recieved hero -- set it to empty values at first
  public recievedHero: Hero = new Hero(0, '', '', false)

  // Message to the User.
  public clientMessage: ClientMessage = new ClientMessage('');

  constructor(private heroService: HeroService) { }

  public findHeroFromService(): void {

    this.heroService.findHero(this.hero).subscribe(data => this.recievedHero = data, // make sure you are setting your data equal here

      error => this.clientMessage.message = 'SOMETHING WENT WRONG');
  }

}
