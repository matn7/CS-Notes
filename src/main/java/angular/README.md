# Angular

## Components

- Header
    - Items on header
- Main area
- Sidebar

## Databinding

- Databinding = Communication


                               Output data
TypeScript Code     --------------------------------->       Template HTML
                    String Interpolation {{ data }}
                    Property binding [property]="data"

                               User Events
                    <----------------------------------
                    Event binding (event)="expression"


                    [(ngModel)]="data"  two way binding


## Directives

- Directives are Instructions in the DOM!

<p lightsUp>5 lights light up</p>

```ts
@Directive({
    selector: '[lightsUp]'
})
export class LightsUpDirective {

}
```

## Property & Event Binding

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
