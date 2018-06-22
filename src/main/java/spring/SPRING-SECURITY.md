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














