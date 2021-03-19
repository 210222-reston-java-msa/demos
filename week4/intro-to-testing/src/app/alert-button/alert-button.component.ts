import { MessageService } from './../message.service';
import { Component, OnInit } from '@angular/core';
import { timer, Observable } from "rxjs";

@Component({
  selector: 'app-alert-button',
  templateUrl: './alert-button.component.html',
  styleUrls: ['./alert-button.component.css']
})
export class AlertButtonComponent implements OnInit {

  // later we will change this to an observable
  content?: Observable<any>

  //content = 'you have been warned';
  hideContent = true;
  severity = 423;

  constructor(private msgService: MessageService) { }

  ngOnInit(): void {
  }

  toggle(): void {
    this.hideContent = !this.hideContent;
  }

  // this is a method to simulate async data 
  toggleAsync(): void {
    // we use subscribe to DO something with the data that is emitted from an Observable
    timer(500).subscribe( () => {
      this.toggle();
    })
  }

  genContent(): void {
    this.content = this.msgService.getContent();
  }

}
