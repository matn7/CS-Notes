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

## Commands
ng new project-name
