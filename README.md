## Yelp Fusion Java Client

[![release](https://badgen.net/badge/version/main/green?icon=github)](https://github.com/stewseo/yelp-fusion-client/tree/main)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.stewseo/yelp-fusion-client?versionPrefix=1.8.0)](https://search.maven.org/artifact/io.github.stewseo/yelp-fusion-client/1.8.0/jar)
[![examples](https://badgen.net/badge/docs/examples/cyan?icon=github)](https://stewseo.github.io/yelp-fusion-client/examples)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=stewseo_yelp-fusion-client&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=stewseo_yelp-fusion-client)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=stewseo_yelp-fusion-client&metric=coverage)](https://sonarcloud.io/summary/new_code?id=stewseo_yelp-fusion-client)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=stewseo_yelp-fusion-client&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=stewseo_yelp-fusion-client)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=stewseo_yelp-fusion-client&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=stewseo_yelp-fusion-client)
<br>

### Connecting to the Yelp Fusion API

- [Create a Yelp Fusion API Key](https://docs.developer.yelp.com/docs/fusion-intro) 
- [Create a YelpFusionClient](https://stewseo.github.io/yelp-fusion-client/examples/clients)

<br>

#### As a Maven Dependency
You can use [Maven's dependency management](https://search.maven.org/search?q=g:io.github.stewseo) to obtain the driver by adding the following configuration in the application's Project Object Model (POM) file:

**Example - Maven**
```xml
<dependencies>
<dependency>
  <groupId>io.github.stewseo</groupId>
  <artifactId>yelp-fusion-client</artifactId>
  <version>1.8.0</version>
</dependency>
</dependencies>
```
#### As a Gradle Dependency
You can use [Gradle's dependency management](https://search.maven.org/search?q=g:io.github.stewseo) to obtain the driver by adding the following configuration in the application's ```build.gradle``` file:

**Example - Gradle**
```gradle
dependencies {
   implementation 'io.github.stewseo:yelp-fusion-client:1.8.0'
}
```
<br>

### Use case
- [search for all restaurants by category in San Francisco](https://github.com/stewseo/yelp-fusion-client/blob/integration-tests/clients/src/integrationTest/java/io/github/stewseo/clients/yelpfusion/SearchBusinessesAndStoreTest.java)
- [Store each business result as a Json Document in Elasticsearch](https://github.com/stewseo/yelp-fusion-client/blob/integration-tests/clients/src/integrationTest/java/io/github/stewseo/clients/yelpfusion/RequestBusinessDetailsAndStoreTest.java)
- [Restaurant Recommendation Engine](https://github.com/stewseo/restaurant-recommendation-system/blob/main/YelpRecommendation.ipynb)


### Features based on [Elasticsearch Java Client](https://github.com/elastic/elasticsearch-java/blob/main/docs/design/0001-model-classes-structure.md)
- [Strongly typed requests and responses for all Yelp Fusion APIs](https://github.com/elastic/elasticsearch-java/blob/main/docs/design/0001-model-classes-structure.md)
- Http transport is handled by a low level rest client.
- Encodes to JSON and decodes from JSON.
  <br>

