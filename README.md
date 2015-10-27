# gbif-api

The GBIF API provides 
  1. The model objects used by the GBIF service interfaces and the internal messaging systems
  2. Enumerations representing standardised vocabularies (country codes, databased enumerations etc)
  3. The Java interface definitions for the public GBIF API (note: each implementation is responsible for mapping to the RESTful URL)
  43. Utilities to simplify common operations when working with model objects (JSON serialization, filtered iterators etc)

## To build the project
```
mvn clean install
```

## Policies applied to this project
  1. Built as Java 6 artifact until the [IPT](https://github.com/gbif/ipt) moves to Java 7.
  2. A pre-commit peer review on all commits, ideally referencing the review in the commit message
  3. All commits must reference a Jira to which they relate
