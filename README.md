# Stackline Technical Assessment

https://en.wikipedia.org/wiki/Kobayashi_Maru

Kirk was the first to beat the Kobayashi Maru test by changing the rules of the test.

A real-world problem solving assessment by creating a microservice (or set of) around the problem space.

## Base Requirements

Given a URL to a gzipped, tab-delimited file containing products and sellers, create a set of microservices responsible for downloading the flat-file, parsing the file into a database, and exposing basic CRUD capability for products.

* Domain is "Product"
* Tab-separated file (gzipped) provided in URL form as the flat-file source for hydrating a database
* Product data attributes:
  * SKU
  * Product Description
  * Seller ID
  * Seller Username
  * Category ID
  * Category Name
* Create a RESTful interface for CRUD operations for the "Product Domain"

## Architecture Design

A big challenge with a test like this is the lack of supporting infrastructure.

Given my current experienced and tech stack acumen, I would probably implement a ScyllaDB (NoSQL/Cassandra) database and Kafka for streaming data, as well as a Kubernetes cluster allowing the usage of containers, as well as some FaaS (OpenFaaS, Nuclio, Kubeless). The entire project could possibly be de-composed into a serverless architecture as well.

### Technology Stack

* Apache Kafka for asynchronous message processing. By using a topic, we keep open the possibility for some refactoring later (think serverless/functions) for multiple consumers, such as splitting indexing into ES and persisting to DynamoDB
* ScyllaDB for scalable, big data, primary persistence
* ElasticSearch is designed for searching your data. Compliments your primary data store
* JVM-based microservices written in Kotlin
  * Micronaut Framework (http://micronaut.io/)
  * Reactor 3 for reactive streams
* Dockerfile for each microservice
* docker-compose.yml provided to spin up the stack
* swagger.yml provided for API documentation
* kubernetes.yml provided just because

### Software Architecture

* CQRS (Command Query Responsibility Segregation)
* Domain-Driven Design
* Ports-and-Adapters
* Reactive (Reactor 3 and akka-streams)
* Google Java Style Guide (see `GoogleStyleGuide.xml`)
* "Tell, Don't Ask" configuration
* Shared code as anti-pattern in microservices
* Asynchronous, event-based via Kafka

### Pragmatic REST Design

* Lowercase, kabob-style, plural nouns for routes
* Paging object for collection endpoints: limit, offset, total, returned
* camelCase properties to cater to wider audience (Java, JavaScript)
* HTTP verbs
  * POST: create, sometimes update.
  * PUT: update, sometimes create if ID can be client-provided (UUID)
  * DELETE: obviously
  * GET: reads/fetch data
* URL/path-based versioning. Versioning per repository (vs branching, packages, etc)
* Security should be a consideration (JWT, OAuth2, Token, HMAC). Will implement only if time
* i18n, not everyone speaks english. Use standard Accept-Language header to support translation

### Microservices

A design decision made on a case-by-case basis is "what size?" to make a microservice. Based on my current experience and patterns put into practice, I implement CQRS by "physically" separating read and write services as they can be maintained and scaled independently.

#### product-read-v1

This represents the read-optimized side of CQRS.

This service is responsible for all read operations for products, such as findings products, fetching a specific product, fetching product categories, etc.

#### product-write-v1

This represents the write-optimized side of CQRS.

This service is responsible for all write operations for products. This is the service used to trigger the streaming batch process of downloading the TDF and importing product data. There is an asynchronous boundary between the write and manager by means of SNS (or Kafka, SQS, other queue/topic technology).

#### product-manager-v1

This service does not expose any REST endpoints (other than internal health, readiness, liveliness, etc), and is responsible for the primary business logic for products, including indexing into elasticsearch and writing out to databases.

## Learnings

* First time using docker-compose
* First time using AWS IAM, SQS, SNS and DynamoDB. Went with what I know instead due to time (Kafka, Scylla)
* Micronaut Framework (released only days ago)
* Pain in the ass figuring out how to build a solution to hand to someone with zero supporting infrastructure
* Initially tried JS solution for the bulk import script, gave up, just not good enough to quickly knock out a JS solution

## Research

In an attempt at doing some pro-active research, searching store.docker.com and github.com returned some other candidates work. Giving myself an edge, my intent is to then provide an improved solution knowing the problem domain.

* https://github.com/morsen/stackline-assessment
* https://github.com/kozubenkob/stackline
* https://github.com/misterrandom/stackline-sample
* https://github.com/datrived/stackline_searchapi
* https://store.docker.com/community/images/morsen/stackline-assessment
* https://store.docker.com/community/images/dtrivedi/stackline_api

* http://www.baeldung.com/spring-reactor
