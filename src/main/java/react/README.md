# React

**The App function is a React Component**

- App Component: Produces JSX and handles user events
- JSX: Set of instructions to tell React what content we want to show on the screen

![React JSX](images/react-jsx.png "React JSX")

![React Decide](images/react-decide.png "React Decide")

- React:
    - Knows how to work with components
    - Called a 'reconciler'
- ReactDOM:
    - Knows how to take instructions on what we want to show and turn it into HTML
    - Called a 'renderer'
- useState:
    - Function for working with React's 'state' system
    - State is used to keep track of data that changes over time
    - Used to make React update the HTML on the screen
    
**Create React project**

```console
npx create-react-app myapp
```

- Brand new React project:
    - Webpack
    - Babel
    - Dev Server

**React Project Structure**

![React Project Structure](images/react-proj-explore.png "React Project Structure")

**Start and Stop React Project**

```console
npm start
```

**Functional Component**

![Functional Component](images/react-component.png "Functional Component")

## JSX

**JSX**

- Special dialect of JS (it is not HTML!)
- Browsers don't understand JSX code! We write JSX then run tools to turn it into normal JS
- Very similar in form and function to HTML with a couple of differences

**JSX vs HTML**

- Adding custom styling to an element uses different syntax
- Adding a class to an element uses different syntax
- JSX can reference JS variables

![JSX vs HTML](images/jsx-vs-html.png "JSX vs HTML")
 