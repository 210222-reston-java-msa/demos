import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AppComponent } from './app.component';

// describe is from Jasmine (our testing framework) and it's describing
// that the following spec (unit tests) are used to test everything about our AppComponent
describe('AppComponent', () => {
  beforeEach(async () => {
    //  A Test Bed => Testing Module => a Class
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule
      ],
      declarations: [
        AppComponent
      ],
    }).compileComponents();
  });

  // this is a single spec (a unit test)
  // BDD --> Behavior Driven Devlopment 
  it('should create the app', () => {
    // a fixture is a repeatable baseline for running tests
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;

    // the logic we expect to pass
    expect(app).toBeTruthy(); // we expecte it to be created
  });

  it(`should have as title 'My New Title'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('My New Title');
  });

  it('should render title', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement;
    expect(compiled.querySelector('h1').textContent).toContain('My New Title');
  });
});
