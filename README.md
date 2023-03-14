# P2

# User Stories - 

## Any User-

1. Any user can register a new account and log in to a valid account.
1. Any user can look view the site's inventory. They can filter items based on price, name, and type.

## Authenticated Users-

1. Authenticated users can add items to shopping cart. Their cart is stored in a database, so if the user reloads the page, this information persists. Users can also manage their cart by editing the quantity of items, and delete items from the cart.
1. Authenticated can attempt to checkout their cart. This checkout attempt will prompt the user for their credit card information. If the information is valid, the checkout attempt is successful and the items will have been purchased.
1. Authenticated users can view their past transactions. These transactions can be filtered by total amount spent and the shipping status.
1. Authenticated users will be able to manage their user information. This includes their address and their password.

 ## Admin-

1. Admins manage the shipping status.
1. Admins can manage site inventory. This includes adding new items and update items (in case there is a sale).

## Schema
![Schema](https://github.com/Michael-Robertson2/Robertson-Michael-Code/blob/main/p2.png)

# Tech Stack

- [Java 8](https://www.oracle.com/java/technologies/java8.html)
  - "Java 8 is a revolutionary release of the world’s #1 development platform."
- [Apache Maven](https://maven.apache.org/)
  - "Apache Maven is a software project management and comprehension tool."
- [MySQLConnector Java](https://dev.mysql.com/downloads/connector/j/)
  - For Java/SQL integration
- [Spring](https://spring.io/)
  - "Spring’s focus on speed, simplicity, and productivity has made it the world's most popular Java framework."
- [JSON Web Tokens](https://jwt.io/)
  - "JSON Web Tokens are an open, industry standard RFC 7519 method for representing claims securely between two parties."
- [JUnit](https://junit.org)
  - "JUnit is a simple framework to write repeatable tests."
- [Mockito](https://site.mockito.org/)
  - "Tasty mocking framework for unit tests in Java"

# Workflow

- Implement Feature
- Test Feature
- Squash (combine) commits as necessary
- Push branch to remote repo
- Create a Pull Request

[Frontend README](https://github.com/Michael-Robertson2/TTT-Frontend/blob/main/README.md)

## Development Setup

- create a resources directory `src/main/resources`
- in `resources` create `applications.properties` file 
  - needs a `salt` and `saltTwo`
- in `resources` create a `application.yaml`

```
server:
  port: *****
  servlet:
    context-path: *****

spring:
  application:
    name: ******
  datasource:
    driver-class-name: org.postgresql.Driver
    url: ******
    username: ******
    password: ******
  jpa:
    database-platform: ********
    show-sql: ******
    hibernate:
      # hibernate will drop and create your tables whenever you start your application.
      ddl-auto: ********
```
