import { MessageService } from './../message.service';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DebugElement } from '@angular/core';
import { AlertButtonComponent } from './alert-button.component';
import { tick, fakeAsync } from '@angular/core/testing';
import { of } from 'rxjs';

// describe is describing what this test suite is all about
describe('AlertButtonComponent', () => {
  let component: AlertButtonComponent;
  let fixture: ComponentFixture<AlertButtonComponent>;
  let de: DebugElement; // Debug element helps us test elements within HTML tree
  let serviceStub: any;
  let service: MessageService;
  let spy: jasmine.Spy;

  beforeEach(async () => {

    // this is a fake object to be returned
    serviceStub = {
      getContent: () => of('you have been warned'),
    }

    await TestBed.configureTestingModule({
      declarations: [ AlertButtonComponent ],
      providers: [{provide: MessageService, useValue: serviceStub}] // this is configuring our Test Bed
    })
    .compileComponents(); // this generates the css and html files associates with the class
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AlertButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    // give our tests the capability of testing elements in HTML tree.
    de = fixture.debugElement;
  });

  // this spec checks that it can be instantiated
  it('should create', () => {
    expect(component).toBeTruthy();
  });

  // it should have a message containing the word "warn"
  it('should have a message containing the word `warn`', () => {
    expect(component.content).toContain('warn');
  });

  // it should have a message that is exactly equal to 'you have been warned
    // test for exact match 
  // it('should have a message that is exactly equal to you have been warned', () => {
  //   //expect(component.content).toBe('you have been warned');
  //   //expect(component.content).toMatch('you have been warned');
  //   expect(component.content).toEqual(`you have been warned`)
  // });


  // it should have a severity level greater than 100
  it('it should have a severity level greater than 100', () => {
    expect(component.severity).toBeGreaterThan(100);
  });

  it('should toggle the message boolean', () => {
    expect(component.hideContent).toBeTruthy();
    component.toggle();
    expect(component.hideContent).toBeFalsy();
  }) 

  //'should toggle the message boolean'
  it('should toggle the message boolean aynchronously', fakeAsync(() => {
    expect(component.hideContent).toBeTruthy();
    component.toggleAsync(); // this method takes 500 miliseconds
    tick(500); // without tick 500 this spec will fail because Angular doens't wiat for the 500 miliseconds to run out
    // before testing
    expect(component.hideContent).toBeFalsy();
  })) 

  it('should  have message content defined from an observable', () => {
    component.content?.subscribe(content => {
      expect(content).toBeDefined(); // check that it's not undefined
      expect(content).toBe('you have been warned'); 
      // even though our actuall Message Service relies on FireBase, 
      // we're using stubbing to intercept the request and NOT make a live request to our DB.

      // we are just making sure that we recieve back observable data (that we can subscribe to)

    })
  })


});
