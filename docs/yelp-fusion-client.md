---
layout: page
title: "Yelp Fusion Java Client"
permalink: /yelp-fusion-client
---
## The yelp fusion java client provides features based on the [Elasticsearch Java Client](https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/current/introduction.html)
<br>

### Features
- Strongly typed requests and responses for all Yelp Fusion API Endpoints.
- Blocking and asynchronous versions of Apis.
- Use of fluent builders and functional patterns to create complex nested structures more conveniently.
- Delegates protocol handling to an http client that takes care of all transport-level concerns. The http client is based on the [Elasticsearch Java Client](https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/current/introduction.html)
- Automatically maps json to/from an application class.

<br>

### Dependency Configuration

https://search.maven.org/artifact/io.github.stewseo/yelp-fusion-client/1.0.3/jar

Gradle
```
dependencies {
    implementation 'io.github.stewseo:yelp-fusion-client:1.0.3'
}
```
Maven
```
<dependency>
    <groupId>io.github.stewseo</groupId>
    <artifactId>yelp-fusion-client</artifactId>
    <version>1.0.3</version>
</dependency>
```
<br>

### [Examples and Use Cases](https://stewseo.github.io/yelp-fusion-client/examples)
