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
 * All changes must go to the **dev** branch for testing before merging to master.
 * A pre-commit peer review on all commits, ideally referencing the review in the commit message. Simple changes can be committed without review.
 * All commits must reference a GitHub issue to which they relate
 * PR are preferred for complex functionality. **Please target the dev branch**.
 
 Dev and master versions must be different to avoid issues with many work-in-progress tasks. When master version is released increment _patch_ version, when released version is merged with development, increment minor version manually. 
 
 **Example** of releasing **dev** branch:
  * Current dev and master versions
    ```
    1) dev version    - 1.7.0-SNAPSHOT
    2) master version - 1.6.0-SNAPSHOT
    ```
  * Merge changes into master
    ```
    1) dev version    - 1.7.0-SNAPSHOT
    2) master version - 1.7.0-SNAPSHOT
    ```
  * Release master and increment _patch_ version
    ```
    1) dev version    - 1.7.0-SNAPSHOT
    2) master version - 1.7.1-SNAPSHOT
    ```
  * Merge changes into dev
    ```
    1) dev version    - 1.7.1-SNAPSHOT
    2) master version - 1.7.1-SNAPSHOT
    ```
  * Bump dev _minor_ version
    ```
    1) dev version    - 1.8.0-SNAPSHOT
    2) master version - 1.7.1-SNAPSHOT
    ```
 **Example** of releasing a fix for **master** branch:
  * Current dev and master versions
    ```
    1) dev version    - 1.8.0-SNAPSHOT
    2) master version - 1.7.1-SNAPSHOT
    ```
  * Release master and increment _patch_ version
    ```
    1) dev version    - 1.8.0-SNAPSHOT
    2) master version - 1.7.2-SNAPSHOT
    ```
  * Merge changes into dev, without version bumping
    ```
    1) dev version    - 1.8.0-SNAPSHOT
    2) master version - 1.7.2-SNAPSHOT
    ```

## Change Log
[Change Log](CHANGELOG.md)

## Documentation

* [JavaDocs](https://gbif.github.io/gbif-api/apidocs/)
* [GBIF.org](https://www.gbif.org/developer/summary)
