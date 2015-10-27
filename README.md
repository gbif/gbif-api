# gbif-api

The GBIF API library provides:
 * The model objects used by the GBIF service interfaces and the internal messaging systems
 * Enumerations representing standardised vocabularies (country codes, databased enumerations etc)
 * The Java interface definitions for the public GBIF API (note: each implementation is responsible for mapping to the RESTful URL)
 * Utilities to simplify common operations when working with model objects (JSON serialization, filtered iterators etc)

## To build the project
```
mvn clean install
```

## Policies applied to this project
 * Built as Java 6 artifact until the [IPT](https://github.com/gbif/ipt) moves to Java 7.
 * A pre-commit peer review on all commits, ideally referencing the review in the commit message
 * All commits must reference a Jira to which they relate

## JavaDocs
http://gbif.github.io/gbif-api/apidocs/
