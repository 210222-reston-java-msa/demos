# ⁉️ Week 4: Angular QC Questions ⁉️

1.  What makes a “single page application” (SPA) different from a normal web page?
    
2.  Explain the difference between server-side and client-side rendering
    
3.  What are some features of the Angular framework?
    
4.  How does TypeScript relate to JavaScript? What are the major benefits of using it over JavaScript?
    
5.  List the data types of TypeScript
    
6.  How would you create a new Angular project?
    
7.  What is a component? How would you create one? List some other commands using the Angular CLI
    
8.  What files make up a component? What is the “spec” file used for?
    
9.  Explain the relevance of npm to Angular projects. Which file does npm use to track dependencies?
    
10.  List some decorators for Angular apps

    - Class decorators, e.g. @Component and @NgModule
    - Property decorators for properties inside classes, e.g. @Input and @Output
    - Method decorators for methods inside classes, e.g. @HostListener
    - Parameter decorators for parameters inside class constructors, e.g. @Inject
    
11.  What is the lifecycle of a component? List some lifecycle hooks:

    - There are 8 different stages in the component lifecycle. 
    - Every stage is called as lifecycle hook event. So, we can use these hook events in different phases of our application to obtain control of the components.
    - LiefeCycleHooks include: ngOnInit, ngOnDestroy, ngDoChecks(), ngAfterViewInit()
    
12.  What is a directive and what are the different types? How to tell these directives apart with syntax?
    
13.  What is the benefit of using a directive like NgClass over the class attribute, or even property binding to the class attribute?
    
14.  What is a pipe? A service?
    
15.  How would you create a custom pipe? What about a service?
    
16.  How does dependency injection work in Angular?
    
17.  What is an Angular module? What properties should you set inside it?
    
18.  What’s the difference between a JavaScript module and Angular module? What are some common Angular modules?

- An NgModule is a class marked by the @NgModule decorator with a metadata object that describes how that particular part of the app fits together with the other parts. NgModules are specific to Angular. While classes with an @NgModule decorator are by convention kept in their own files, they differ from JavaScript modules because they include this metadata.
- [NG Module vs. JS Module](https://angular.io/guide/ngmodule-vs-jsmodule)
    
19.  How would you run your unit tests for an Angular project?
    
20.  How have you used the HttpClient? What methods does it have and what do they return?
    
21.  What is an Observable? What’s the difference between it and a Promise?

    > Promises deal with **one** asynchronous event at a time, while observables handle a **sequence** of asynchronous events over a period of time.
    
22.  What forms of data binding does Angular support? Explain the syntax for each
    
23.  What does Webpack do for your ng project?

- Webpack is a tool that lets you compile JavaScript modules, also known as module bundler. Given a large number of files, it generates a single file (or a few files) that run your app. It can perform many operations: helps you bundle your resources
    
24.  How would you implement routing in your project?
    

<hr> 


# Angular Study Guide


#### But first, What's a framework?
A framework is a platform for developing software applications. It provides a foundation on which software developers can build programs for a specific platform. A framework may also include code libraries, a compiler, and other programs used in the software development process.

## What's Angular?
- It is an open-source TypScript-based frontend framework developed by Google (in 2010).
- It uses a component structure that represents a view on a webpage
- We use Angular to create SPA's (Single Page Applications)
    - SPA's allow for faster and more responsive web pages and a better user-experience

### History of Angular
- Created in 2010 as AngularJS
- Then in 2016 it was rewritten to support TypeScript and Angular 2 was released
- About every 6 months a new version of Angular is released
- We will use Angular 11.

### What makes an Angular app?
- **TypeScript** framework developed and maintained by Google.
- Reusbale code through the inclusion of **modules**
- Dynamics views through the usage of **components** and **templates**
- DOM manipulation is handled through **Directives**
- Single-page applications are created through the use of **Routing**
- Handle **Dependency Injection** through the use of **Services**

## TypeScript
- it is open-source and invented & maintained by **Microsoft**
- it is a super-set of JS
- it is **strictly typed**
- Browsers themselves cannot interpret TS, so it must be **transpiled into JS**.
    > This is done with the TypeScropt compiler (tsc).
- It has syntax closer to higher level languages like Java, therfore it gives us compilation errors.

## Node JS
- It is a JS interpreter/server environment that allows us to run JS outside of the browser.

### What is NPM?
- Node Package Manager
- It is included in the download of Node.js and consists of a CLI (Command Line Interface) that interacts with a remote registry
- Similar to maven, it can manage dependencies and versions
    - This is done through the `package.json` file (which acts like the `pom.xml`).

### Angular CLI (Command Line Interface)
- This is the CLI that is used to scaffold and build the Angular Project using modules
- `ng` is the Angular command for the CLI
- to install Angualar, we call `npm install -g @angular/cli`

## Webpack
- The angular CLI uses a tool called **webpack** which is a build automation tool.
- it minifies all of our JS scripts, HTML and CSS files and bundles them together
- The webpack traverses our application looking for `import` statements and builds a dependency graph

## `package.json`
- Think of the pom.xml file within your Angular project
- The package.json file is stored in the root of your application
- This file is used to give information to npm (node package manager) and identify all the project's dependencies and handle them.

## Angular Components
Components are the most basic block of an Angular app.  An Angular App contains a tree of Angular Components
- Components are essentially classes that interact with the .html file of the component which gets displayed to a browser
- The file structure of a component consists of the following:
    1. `mycmp.component.css` -- the component's private styling sheet
    2. `mycmp.component.html` -- HTML Template
    3. `mycmp.component.ts` -- Here we define the module, its properties, lifehooks, etc.
    4. `mycmp.component.spec.ts` -- unit testing file

- To create a component run: `ng g c mycomponent`
- As soon as we create a component, all changes are added to our `app.module.ts` file
- As soon as the component is created, ngOnInit method is created
    - this method is called by default when the class is executed.
- It also has a constructor
- the `app` component is the **Parent component** and any new components that we create and the **Child components**

## Data-Binding
- Data binding is a technique to link your data to your view layer.
- By binding a variable, you are telling the framework (in this case, Angular) to watch it for changes.
    - If changes are detected, the framework will update the view.

#### One-Way Binding 
- One-way binding will bind the data from the component to the view (DOM) or from the view to the component.
- This is unidirectional (we change one thing)
- **For example**: *Interpolation* -> Interpolation refers to binding expressiong into marked up language (we turned the property of the `app component` into HTML text (as the title like so `{{title}}`)
    - Interpolation allows you to incorporate properties and embed them into an HTML file with {{}};
- **Class Binding** Class binding is used to set a class property of a view element

#### Two-Way Data Binding
- 2-way data binding in Angular will help users to exchange data from the component to view, and from view to component (bidirectional)
- 2-way databinding is a combo of Property Binding and Event Binding
- 2-way binding can be achieved with the `[(ngModel)]` directive...think banana in a box!

## Directives
Directives change the appearence behavior or layout of the DOM (Document Object Model)

#### Structural Directives
- These change the DOM layout by adding/removing DOm elements
- `ngFor` and `ngIf`

#### Attribute Directives
- These change the appearence or *behavior* of a particular element, component, or another directive.
- We typically build these ourselves
##### Example
The `@Input` directive is an example of and Attribute Directive.
- Decorator that marks a class field as an input property and supplies configuration metadata. The input property is bound to a DOM property in the template

## @Decorators
*These are similar to the purpose of annotations in Java*
- The **@Component** decorator tells Angular that we want a class to be treated as a component
    - It provides the metadata for how this particular Component Class should be processed, used, and instantiated.
- The **@NgModule** decorator takes a metadata object that describes how to compile a component's template and how to create an injector at runtime.
    - An `NgModule` is a class marked by the **@NgModule** decorator.
    
### `AppModule`: The Root Module
An Angular **module class** describes how the application parts fit together. Every application has at least one Angular module, the root module that you bootstrap to launch the application. You can call it anything you want. The conventional name is `AppModule`.

#### `@NgModule` Decorator
The `@NgModule` decorator identifies AppModule as an Angular module class (also called an `NgModule` class). 
- `@NgModule` takes a metadata object that tells Angular how to compile and launch the application.
    - `imports` — the BrowserModule that this and every application needs to run in a browser.
    - `declarations` — the application's lone component, which is also ...
    - `bootstrap` — the root component that Angular creates and inserts into the index.html host web page.

---

# Dependency Injection
**Dependency injection** is a technique in which an object receives other objects that it depends on. These other objects are called dependencies.
- The "injection" refers to the passing of a dependency (a service) into the object that would use it.
- Dependencies are services or objects that a class needs to perform its function.

### Services
Angular services are singleton objects that get instantiated only once during the lifetime of an application.
- Services are a great way to share information among classes that don't know each other. 
- Services can depend on other services.
- The `@Injectible` decorator defines this as a provider, and its metadata specifies that it will be provided at the root level. 

---

## Using *Observables* to pass values
**Observables** provide support for passing messages between parts of your application. They are used frequently in Angular and are the recommended technique for event handling, asynchronous programming, and handling multiple values.

The **Observer Pattern** is a software design pattern in which an object, called the subject, maintains a list of its dependents, called observers, and notifies them automatically of state changes. 
    - This pattern is similar (but not identical) to the [publish/subscribe](https://en.wikipedia.org/wiki/Publish%E2%80%93subscribe_pattern) design pattern.


## In-App Navigation: Routing to Views
In a Single Page Application, we change what the user sees by showing or hiding portions of the display which corresponds to particular components...instead of reaching out to a server to get a new page.
    - We use the **Angular Router** which handles navigation from one view to the next
    - **Router** enables navigation by interpreting a browser URL as in *instruction* to change the view.
