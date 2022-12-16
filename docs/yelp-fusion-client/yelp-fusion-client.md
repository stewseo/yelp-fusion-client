---
layout: page
title: "Yelp Fusion Java Client"
permalink: /yelp-fusion-client
---

## Yelp Fusion Java Client
<br>

1
[![release](https://badgen.net/badge/version/1.0.52/green?icon=github)](https://github.com/stewseo/stewseo.github.io/tree/version-1.0.5)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.stewseo/yelp-fusion-client?versionPrefix=1.0.52)](https://search.maven.org/artifact/io.github.stewseo/yelp-fusion-client/1.0.52/jar)
[![examples](https://badgen.net/badge/docs/examples/cyan?icon=github)](https://stewseo.github.io/yelp-fusion-client/examples)


### Features based on the [Elasticsearch Java Client](https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/current/introduction.html)
- Strongly typed requests and responses for all Yelp Fusion APIs.
- Blocking and asynchronous versions of Apis.
- Use of fluent builders and functional patterns to create complex nested structures.
- Delegates protocol handling to an http client that takes care of all Http transport.
- Automatically maps json to/from application classes.

<br>

#### As a Maven Dependency
You can use [Maven's dependency management](https://search.maven.org/search?q=g:io.github.stewseo) to obtain the driver by adding the following configuration in the application's Project Object Model (POM) file:

**Example - Maven**
```xml
<dependencies>
<dependency>
  <groupId>io.github.stewseo</groupId>
  <artifactId>yelp-fusion-client</artifactId>
  <version>1.0.52</version>
</dependency>
</dependencies>
```
#### As a Gradle Dependency
You can use [Gradle's dependency management](https://search.maven.org/search?q=g:io.github.stewseo) to obtain the driver by adding the following configuration in the application's ```build.gradle``` file:

**Example - Gradle**
```gradle
dependencies {
   implementation 'io.github.stewseo:yelp-fusion-client:1.0.52'
}
```
<br>


