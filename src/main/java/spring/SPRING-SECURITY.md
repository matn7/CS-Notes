# Spring Security

- Spring Framework for Security
- Using Servlet filters in background
- Declarative and programmatic methods of securing

## Spring Security - Servlet Filters
- Servlet filters are used to pre-process / post-process web requests
- Servlet filters can route web requests based on security logic
- Spring provides secrity functionality with servlet filters

## Spring Security - Overview

    +---------+             +----------+    +---------------+
    | WEB     |-------------| SPRING   |--->| Protected web |
    | BROWSER |<------------| SECURITY |----| Resource      |
    |         |             | FILTERS  |    +---------------+
    +---------+             +----------+
                                        \
                                         +----------------------------+    +-------------------------+
                                         | app security configuration |<-->| users, passowrds, roles |
                                         +----------------------------+    +-------------------------+

## Security concepts
- Authentication
    - Check user id and password with credentials stored in app / database

- Authorization
    - Check if user has an authorized role

## Declarative Security
- Security constrains defined in configuration
    - Java config (@Configuration)
    - Spring XML config

- Provides separation of concerns between application code and security

## Programmatic Security
- Spring Security API for custom application config
- Customization for specific app requirements

## Login Methods
- HTTP Basic Authentication
- Default login form
- Custom login form

### HTTP Basic Authentication
- Build in for Brovser
- Default login form

## Authentication and Authorization
- In-memory
- JDBC
- LDAP
- Custom / Pluggable

# Spring Security - Config

## Java Configuration

- maven dependencies
    - spring-webmvc
    - jstl
    - javax.servlet-api
    - javax.servlet.jsp-api

- Enabling the MVC Java Config
    - `@EnableWebMvc`
        - Provides similar support to <mvc:annotation-driven /> in XML.
        - Adds conversion, formatting and validation support
        - Processing of `@Controller` classes and `@RequestMapping` methods

```java
@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.panda.springsecurity")
public class AppConfig {
    // bean for view resolver
    @Bean
    public ViweResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }
}

```

### Web App Initializer
- Spring MVC provides support for web app initializer
- Code is automatically detected
- Code used to initialize the servlet container
- `AbstractAnnotationConfigDispatcherServletInitializer`
    - Extend this calss
    - Override methods
    - Specify servlet mapping and location of app config

```java
public class SpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?> getRootConfigClasses() {
        return null;
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { AppConfig.class };
    }

    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
```

### Spring Controller

```java
@Controller
public class DemoController {
    @GetMapping("/")
    public String showHome() {
        return "home";
    }
}
```

## Spring Security - Configuration
- maven dependency
    - spring-security-web
    - spring-security-config

- Spring Security Web App Initializer
    - Code is used to initialize the servlet container
    - Special class to register to Spring Security Filters
        - `AbstractSecurityWebApplicationInitializer`

```java
public class SpringSecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

}
```

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurarAdapter {
    // ...
}
```

## Custom Login

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurarAdapter {
    // ...

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyResult().authorized()
            .and()
            .fromLogin()
                .loginPage("/loginPage")
                .loginProcessingUrl("/authenticatePlease")
                .permitAll();
    }
}
```
- Login Controller
```java
@Controller
public class LoginController {
    @GetMapping("/loginPage")
    public String login() {
        return "login";
    }
}

```
- Login Form
```html
<form:form action="${pageContext.request.contextPath}/authenticatePlease" method="POST">
    <!-- ... -->
</form:form>
```

### Spring Security Logout
- Add logout support to Spring Security Configuration
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurarAdapter {
    // ...

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyResult().authorized()
            .and()
            .fromLogin()
                .loginPage("/loginPage")
                .loginProcessingUrl("/authenticatePlease")
                .permitAll();
            .and()
            .logout().permitAll();
    }
}
```

```html
<form:form action="${pageContext.request.contextPath}/logout" method="POST">
    <input type="submit" value="Logout" />
</form:form>
```

### Spring Security - Cross Site Request Forgery (CSRF)
- CSRF
    - A security attack where an evil website tricks you into executing an action on a web application that
    you are currently logged in.
- Logged in an e-commerence app and buy unwanted stuff

- Protect
    - Embed additional authentication ata/token into all HTML forms
    - On subsequent requests, web app will verify token before processing

- enabled by default in Spring Security
- Spring Security uses te Synchronized Token Pattern
    - Each request includes a session cookie and randomly generated token
- For request processing, Spring Security verifies token before processing
- All handles by Spring Security Filters

- Use for
    - Any normal browser web requests
    - Building a service for non-browser clients

- Use Spring Security CSRF protection
    - For form submission use POST
    - Include CSRF token in form submission
    - `<form:form>` automatically adds CSRF token

```html
<form action="..." method="POST">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}" />
</form>

```

