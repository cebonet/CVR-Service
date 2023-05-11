# Sample Java 17 Project

This is a sample Java 17 Project, it calls the Danish CVR registry and saves them locally for faster retrieval next time. It will save a local copy of the response to the database or Redis cache if enabled. Here is an overview of used tech and capabilities:

- Java 17 Spring boot 3 (Spring Framework 6)
- API for fetching from registry
- API for fetching only from local database
- Swagger Documentation
- JPA with Postgress
- Redis added as optional caching layer
- Unit testing a service layer
- Test Container with Postgress for db integrations test

## Before Run

Run the docker-compose first to spin up database and Redis instances.
