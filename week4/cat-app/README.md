# Steps we Took to Create this Cat App:

1. Generate the proect scaffolding with `ng new cat-app`.

2. We strated examining the root application comoponent (also called out app shell).

3. We generated our own component called `Cats`. We ran `ng g c cats`. On this component we listed Cats that we got from a static array called `mock-cats.ts`.  This represented some data source.

    > We needed to create an interface to represent what each Cat object would look like. `cat.ts`

4. We implemented directives and `*ngFor` to create an HTML element for each Cat object.

5. We added private css styles for our new component. 

6. We then delegated the `cat-detail` functionality to seperate component. This was a feature component.  At first we used the @Input decorator, so that every cat we clicked would then become the `cat?` property of the cat-detail component.

7. Previously, the cats component just had an array of cats which it would display...We then added a **Service** with the command `ng g s cat`.  This Service's job was to provide the data to our cats component.

    > The **CatService** essentially feeds data to our component which displays it.  We evolved this to incorporate an HTTPClient Service when we created an in-memory DB that owuld intercept HTTP requests.

8. We created a **Dashboard** Component.  This displayed our top 4 cats which we fetched by ALSO injecting the Cat Service into this component.

9. We introduced Routing. Since the AppRoutingModule was already created for us, we gave it the routes (paths) to load those components.

    > We had to do this by editing the `routes []`;

10.  We converted our static array to an in-memory DB.  We ran `npm install angular-in-memory-web-api --save`. **We will NOT be using this when we have a backend**.

    >  The purpose of this was emulate a Server that we coud send HTTP requests to and retrieve an HTTP response from.

    > In the future we will contact services in our server side located at a URL similar to: 
    `http://localhost:8080/CatApp/detail/16`

11. We evolved our `CatService` to make HTTP get requests to our "server".

12. Now, we will fulfill the rest of the CRUD methods we can use HTTP for to make requests to a server. (Create, updated, delete...we already have read.)



# CatApp

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 11.2.4.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.
