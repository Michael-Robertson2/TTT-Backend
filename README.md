# P2

# User Stories - 

## Any User-

1. Authentication: User can signup/login/logout

1. Items for sale: User(Authenticated or non) can look at items for sale and search by name, filter by price.

## Customer Users-

1. Shopping Cart: User(Authenticated) can add items to shopping cart and the app remembers it next time you login. User can view all the items in their shopping cart.  User can delete items in the shopping cart.  Shopping cart uses an integer column to store "state".
1. Checkout: User(Authenticated) can fill in form and submit billing info.  After submitting billing info, items in the shopping cart will move to a different "state".
1. Manage Information- Saved Address, Payment
1. View Purchase History

 ## Admin-

1. Payment(APPROVED,DENIED)
1. Shipping(PENDING, SHIPPED, DELIVERED)
1. Manage items

## Schema
![Schema](https://github.com/221114-Java-React/TTT-Frontend/blob/main/erd.png)

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

