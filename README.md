# Rental-Helper
> A go-to tool for realtors to collect organized property listings.

Designed a web scraper for realtors with organized property listings and parameters which can be analyzed to determine sales and prospective buyers using Java and Jsoup package. 

Stored the housing data with MongoDB database deployed on AWS and made it easily accessible to the analytics team using MongoDB Compass. 

Automated the application to scrape new listings based on desired update frequency and location code

## Installation

+ Prerequisites:
  + Install the Java 8 or later version of [Java](https://java.com/) and [Maven](https://maven.apache.org/download.html)

## How to run this project

To build this project locally:

```
git clone git@github.com:dar1enyang/Rental-Helper.git
```

Run maven to build package:

```maven
mvn package
```

Run this project:

```
java -jar /your-path/rental-helper/target/rental-helper-0.0.1-SNAPSHOT.jar
```

## Dependencies

#### JSoup 

jsoup is a Java library for working with real-world HTML. It provides a very convenient API for extracting and manipulating data, using the best of DOM, CSS, and jquery-like methods. jsoup implements the WHATWG HTML5 specification, and parses HTML to the same DOM as modern browsers do.

#### Apache Commons Net 

Apache Commons Net library contains a collection of network utilities and protocol implementations, used trustmanger to set default SSL socket factory

#### Morphia  

Java Object Document Mapper for MongoDB





<!-- Markdown link & img dfn's -->

[jsoup-image]: https://d2j3q9yua85jt3.cloudfront.net/img/7be73b50a49c0f9429757ad3d670c58a
[morphia-image]: https://d2j3q9yua85jt3.cloudfront.net/img/1c7b89fc3793aa68d9ccd47edb1558d2
[Common-net-image]: https://d2j3q9yua85jt3.cloudfront.net/img/afbc279e1391b649c23fc052822c0447