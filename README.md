# GBIF API

The GBIF API library provides:
 * The model objects used by the GBIF service interfaces and the internal messaging systems
 * Enumerations representing standardized vocabularies (country codes, databased enumerations etc)
 * The Java interface definitions for the public GBIF API (note: each implementation is responsible for mapping to the RESTful URL)
 * Utilities to simplify common operations when working with model objects (JSON serialization, filtered iterators etc)

## To build the project
```
mvn clean install
```

## Policies
 * All changes must go to the *dev* branch for testing before merging to master.
 * A pre-commit peer review on all commits, ideally referencing the review in the commit message. Simple changes can be committed without review.
 * All commits must reference a GitHub issue to which they relate
 * PR are preferred for complex functionality. *Please target the dev branch*.

## Change Log
[Change Log](CHANGELOG.md)

## Documentation

* [JavaDocs](https://gbif.github.io/gbif-api/apidocs/)
* [GBIF.org](https://www.gbif.org/developer/summary)
