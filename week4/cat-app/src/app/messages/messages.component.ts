import { MessageService } from './../services/message.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  // we are making this service public because we will bind it to the template (html page a.k.a skeleton)
  constructor(public messageService: MessageService) { }

  

  ngOnInit(): void {
  }

}
