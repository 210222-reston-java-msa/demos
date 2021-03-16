import { Component } from '@angular/core';

@Component({
  selector: 'app-root', // this is for styling (in the global styles, or for
  // featuring this component somewhere else)
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  // This Class uses a Component Directive to specify that it's a building block
  // the component.ts file stores properties, functions, and metadata
  title = 'The Ultimate Cat App';
}
