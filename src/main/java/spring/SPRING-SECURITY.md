# Spring Security

- Spring Framework for Security
- Using Servlet filters in background
- Declarative and programmatic methods of securing

## Spring Security - Servlet Filters

- Servlet filters are used to pre-process / post-process web requests
- Servlet filters can route web requests based on security logic
- Spring provides security functionality with servlet filters

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
    - Java config `@Configuration`
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

- Build in for Browser
- Default login form

## Authentication and Authorization

- In-memory
- JDBC
- LDAP
- Custom / Plugable

## Spring Security - Config

### Java Configuration

- maven dependencies
    - spring-webmvc
    - jstl
    - javax.servlet-api
    - javax.servlet.jsp-api

- Enabling the MVC Java Config
    - `@EnableWebMvc`
        - Provides similar support to `<mvc:annotation-driven/>` in XML.
        - Adds conversion, formatting and validation support
        - Processing of `@Controller` classes and `@RequestMapping` methods

```java
@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.panda.springsecurity")
public class AppConfig {
    // bean for view resolver
    @Bean
    public ViewResolver viewResolver() {
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
    - Extend this class
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

### Spring Security - Configuration

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
public class SecurityConfig extends WebSecurityConfigurerAdapter {
}
```

### Custom Login

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurarAdapter {
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

**Login Controller**

```java
@Controller
public class LoginController {
    @GetMapping("/loginPage")
    public String login() {
        return "login";
    }
}

```

**Login Form**

```html
<form:form action="${pageContext.request.contextPath}/authenticatePlease" method="POST">
</form:form>
```

### Spring Security Logout

- Add logout support to Spring Security Configuration

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurarAdapter {
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
    - Logged in an e-commerce app and buy unwanted stuff
- Protect
    - Embed additional authentication aka/token into all HTML forms
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

### Spring Security - Access based on roles

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/").hasRole("footballer")
        .antMatchers("/zoo/**").hasRole("zookeeper")
        .and()
        .formLogin()
}
```

### Spring Security - custom access denied page

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    // ...
        .exceptionHandling()
            .accessDeniedPage("/noop");
}

```

### Spring Security - display content based on roles

```html
<security:authorize access="hasRole('zookeeper')">
    <!-- ... -->
</security:authorize>
```

### Spring Security - JDBC

- Spring Security can read user account info from database

```sql
CREATE TABLE users (
    username varchar(60) NOT NULL,
    password varchar(60) NOT NULL,
    enabled tinyint(1) NOT NULL.
    PRIMARY KEY (username)
) ENGINE = InnoDB DEFAULT CHARSET=latin1;

```

```sql
INSERT INTO users VALUES
('brajan', '{noop}haslo',1),
('samara', '{noop}haslo',1);
```

- {noop} - encoding algorithm id

```sql
CREATE TABLE authorities (
    username varchar(60) NOT NULL,
    authority varchar(60) NOT NULL,
    UNIQUE KEY authorities_idx_1 (username, authority),
    CONSTRAINT authorities_ibfk_1
    FOREIGN KEY (username)
    REFERENCES users (username)
) ENGINE = InnoDB DEFAULT CHARSET=latin1;
```

```sql
INSERT INTO authorities
VALUES
(brajan, ROLE_MANAGER),
(samara, ROLE_ADMIN);
```

- maven
    - mysql-connector-java
    - c3p0

**mysql.properties**

```properties
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/database_panda?useSSL=false
jdbc.user=brajan
jdbc.password=gdziejestsamara

connection.pool.initialPoolSize=5
connection.pool.minPoolSize=5
connection.pool.maxPoolSize=20
connection.pool.maxIdelTime=3000
```

- DataSource configuration

```java
@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.panda")
@PropertySource("classpath:mysql.properties")
public class AppConfig {
    @Autowired
    private Environment env; // hold data read from properties file

    @Bean
    public DataSource securityDataSource() {
    	// create connection pool
    	ComboPooledDataSource dataSource = new ComboPooledDataSource();

    	try {
    		dataSource.setDriverClass(env.getProperty("jdbc.driver"));
    	} catch (PropertyVetoException e) {
    		throw new RuntimeException(e);
    	}
    	// set database connection props
    	dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        dataSource.setUser(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.password"));

    	// set connection pool props
		dataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		dataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		dataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		dataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

    	return dataSource;
    }

}
```

- Update Spring Security to use JDBC

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenicationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

}

```

## Spring Security - Password Encryption

- bcrypt algorithm
    - One way encrypted hashing
    - Adds random salt to password for protection
    - Includes support to defeat from brute force attacks

- Password column at leat 68 characters wide
    - {bcrypt} - 8 chars
    - encodedPassword - 60 chars


```sql
('brajan', '{bcrypt}$2a$sdasd$50sdmkMAKdsmiamLSLM',1),
('samara', '{noop}$2a$sdasd$50sdmkMAKdsmiamLSLM',1);
```


























