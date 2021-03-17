import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Injectable } from '@angular/core';

@Injectable({ // I will be injecting this service (this DEPENDENCY) into my MessagesComponent
  providedIn: 'root'
})
export class MessageService {

  // we give it one property -- a string of messages
  messages: string[] = [];

  constructor() { }

  // This message adds a msg to our messages property ( which is a string array)
  add(msg: string) {
    this.messages.push(msg);
  }
  // this method is called in our CatService.getCats() method
  // so that we can see everytime that we fetch cats

  // this "clears our fake cache"
  clear() {
    this.messages = [];
  }

}
