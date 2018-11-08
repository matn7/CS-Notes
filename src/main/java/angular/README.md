# Angular

- Angular is a JavaScript Framework which allows you to create reactive Single-Page-Applications
- Every changes is render in the browser
- Metadata is @ decorator which instruct Angular how to deal with class
    - @NgModule
    - @Component
    - @Injectable
    - @Input
    - @output

***

## Project setup

- NodeJS : bundle and optimize project, uses npm to add dependencies

```bash
$ npm install -g @angular/cli@latest
$ ng new my-first-app
$ cd my-first-app
$ ng serve
```

***

## TypeScript

- TypeScript : More features than vanilla JS (Types, Classes, Interfaces).
- TypeScript is compiled to JavaScript, handled by CLI.

***

## Bootstrap styling

```bash
$ npm install --save bootstrap@3
```

***

## How Angular works

**index.html** - single page that is rendered contains <app-root></app-root> <br/>
**main.ts** - bootstraping AppModule.ts


## Components

- Types of directives in Angular
    - Components directives
    - Structural directives
    - Attribute directives

- Key part of application
    - AppComponent - root component
- Header
    - Items on header
- Main area
- Sidebar
<br/>

### Component decorator

```ts
@Component({
    selector: 'app-servers',
    templateUrl: './servers.component.html'
})
export class ServersComponent implements OnInit {
```

### To use component

```ts
@NgModule({
  declarations: [
    AppComponent,
    ServersComponent
  ],
  imports: [
    BrowserModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
```

### Display component in template

```html
<div class="container">
    <app-servers></app-servers>
</div>
```

### Creating component using CLI

```bash
$ ng g c videogames
```

### Components template and styles

```ts
@Component({
    selector: 'app-server',
    template: '
        <app-server></app-server>
        <app-server></app-server>',
    styles: [`
        .online {
            color: white;
        }
    `]
})
export class ServerComponent {
```

### Component selector (unique identifier of component)

```ts
@Component({
    selector: '[app-servers]', // enclosed attribute in []
    selector: '.app-servers', // enclosed attribute in []
})
```

- Use in template

```html
<app-servers></app-servers>
<div app-servers></div>
<div class="app-servers"></div>
```

***

## Databinding

- Databinding = Communication

```
                               Output data
TypeScript Code     --------------------------------->       Template HTML
                    String Interpolation {{ data }}
                    Property binding [property]="data"

                               User Events
                    <----------------------------------
                    Event binding (event)="expression"


                    [(ngModel)]="data"  two way binding
```

- Supported binding (DOM Component):
    - {{ value }}
    - [property] = value            component -> template
    - (event) = "functionHandler"   template -> component
    - [(ng-model)] = "property"     template <-> component

### String Interpolation

```ts
userIP: number = 11.11.10.10;
userLocation: string = 'Nevada';
enableButton: boolean = false;

constructor() {
    this.enableButton = true;
}

getUserLocation() {
    return this.userLocation;
}
```

```html
<p>{{ 'User' }} address {{ userIP }} / {{ getUserLocation() }} </p>
```

### Property binding

```html
<button
    class="btn btn-primary"
    [disabled]="!enableButton">Submit</button>
<p>{{ enableButton }}</p>
<p [innerText]="enableButton"></p>>
```

### Event binding

```ts
buttonSays: string = "Waiting";
onButtonClicked() {
    this.buttonSays = "Button was clicked";
}
```

```html
<button
    class="btn btn-primary"
    [disabled]="!enableButton"
    (click)="onButtonClicked">Click me</button>
<p>{{ buttonSays }}</p>
```

- Passing data using event binding $event

```html
<label>Text to display</label>
<input
    type="text"
    class="form-control"
    (input)="onUpdateButton($event)">
<p>{{ customerName }}</p>

<button
    class="btn btn-primary"
    [disabled]="!enableButton"
    (click)="onButtonClicked">Click me</button>
<p>{{ buttonSays }}</p>
```

```ts
customerName = '';

onUpdateButton(event: Event) {
    console.log(event);
    this.customerName = (<HTMLInputElement>event.target).value;
}
```

### Two way binding

- To use two way binding FormsModule need to be added to imports[] array in the AppModule

```html
<input
    type="text"
    class="form-control"
    [(ngModel)]="customerName">
<p>{{ customerName }}</p>
```

***

## Directives

- Directives are Instructions in the DOM!
    - Components
- <p lightsUp>5 lights light up</p>

```ts
@Directive({
    selector: '[lightsUp]'
})
export class LightsUpDirective {

}
```

### ngIf (structural directive)

```html
<p *ngIf="userCreated; else noUser">{{ customerName }}</p>
<ng-template #noUser>
    <p>No customer</p>
</ng-template>
```

### ngStyle (attribute directive)

```ts
getColor() {
    return this.status === 'online' ? 'green' : 'red';
}
```

```html
<p [ngStyle]="{ 'background-color' : getColor() }">User status</p>
```

### ngClass, dynamically add or remove css classes

```html
<p [ngStyle]="{ 'background-color' : getColor() }"
   [ngClass]="{ online: userStatus === 'online'  }">User status</p>
```

### ngFor

```html
<app-user *ngFor="let user of users"></app-user>
```

***

## Property & Event Binding

HTML Elements :arrow_forward: Directives :arrow_forward: Components

Native Properties & Events  :arrow_forward: Custom Properties & Events  :arrow_forward: Custom Properties & Events

## Lifecycle

1. ngOnChanges              Called after a bound input property changed
2. ngOnInit                 Called once the component is initialized
3. ngDoCheck                Called during every change detection run
4. ngAfterContentInit       Called after content (ng-content) has been projected into view
5. ngAfterContentChecked    Called every time the projected content has been checked
6. ngAfterViewInit          Called after the content's view (and child views) has been initialized
7. ngAfterViewChecked       Called every time the view (and child views) has been checked
8. ngOnDestroy              Called once the component is about to be destroyed

## Attribute vs Structural

- Attribute Directives
    - Looks like normal HTML Attribute
    - Only affect element they are added to

- Structural Directives
    - Looks like normal HTML Attribute, have a leading *
    - Affect whole area in DOM

## Services & Dependency Injection

- Get info about your services from @NgModule provider so that it can use the dependencies in components
- Services are singletons shared across components
- Service is place where app business logic is
- Should has only one purpose
- @Injectable annotation to make service ready to be injected

### Hierarchical Injector

- AppModule : Same Instance of Service is available Application-wide
- AppComponent : Same Instance of Service is available from all Components (but not for other Services)
- Any other Component : Same Instance of Service is available for the Component and all its child components

## Routing

- Relative path, appends to current path

## Observables

- Observable : data sources (user input) events, Http Requests, triggered in code<br/>

:arrow_right:

- Observer : subscribe, Handle Data, Handle Error, Handle Completion

## Angular and forms

### Template driven

- Angular infers the Form Object from the DOM

### Reactive

- Form is created programmatically and synchronized with the DOM

## Pipes

- Allows to transform output in template

```html
<p>{{ username | uppercase }}</p>
```

- Create custom pipe, implements PipeTransform, override transform method
- Pipe arguments after :, for example shorten:8

## Compilation

### Just in Time

Development :arrow_forward: Production :arrow_forward: App download in browser :arrow_forward: Parses and compiles template to Java Script

### Ahead of Time

Development :arrow_forward: Parses and compiles template to Java Script :arrow_forward: Production :arrow_forward: App download in browser

- Faster Startup, parsing and compilation doesn't happen in browser
- Templates checked during Development
- Smaller File Size

## Deployment

- Build App
- AOT Compilation
- Set base element
- Return index.html in case of 404 error

## Questions

- convert String to number use `+`<br/>
this.id = +params['id'];

- Make link active<br/>
routerLinkActive="active"


## Commands
ng new project-name <br/>
npm install <br/>
ng g d directive-name <br/>
ng g c page-not-found <br/>
npm install --save rxjs-compat <br/>
<br/><br/>
**Ahead of time compilation** <br/>
ng build --prod --aot <br/>
ng build --prod --aot --base-href /application/ <br/>
ng build --watch
npm install rxjs-compat --save

// React, Redux, Axios
npm i redux react-redux redux-thunk
npm i axios


## Change detector

- Each component has own change detector
- You can eventually also trigger manually the change detector
- When a component change it triggers change detector in all other components
- changeDetection input parameter of @Component, you can change strategy
- Zone.js library for Angular2 is used in change detection
- Zone trigger change detection for:
    - DOM Events is fired
    - XHR call get resolved
    - Timer is triggered (setInterval)

## Http call

- HttpModule
    - @angular/http
    - import HttpModule in AppModule.ts
    - Http Module use XMLHttpRequest (XHR) to transfer data from the server to the client
    - RequestOptions to add options and headers to the calls
- RequestHeader
- RequestOptions
- Response

- Supported REST methods
    - GET
    - POST
    - PUT
    - DELETE
    - PATCH


## Single page application

- Spring boot application packaging war
- angular.json
```json
"outputPath": "../src/main/resources/static",
"outputPath": "dist",
```






















