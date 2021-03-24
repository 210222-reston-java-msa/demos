import { ClientMessage } from './../../models/client-message.model';
import { Hero } from './../../models/hero.model';
import { HeroService } from './../../services/hero.service';
import { Component, OnInit } from '@angular/core';



@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  title = 'Register Hero'

  // Constructor Injection
  constructor(private heroService: HeroService) { }

  // For databinding
  public hero: Hero = new Hero(0, '', '', false);

  // Client message to the user
  public clientMessage: ClientMessage = new ClientMessage('');

  public registerHeroFromService(): void {

    this.heroService.registerHero(this.hero).subscribe(data => this.clientMessage = data, 
      
      error => this.clientMessage.message = 'SOMETHING WENT WRONG!');
      
  }
  

}
