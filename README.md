## Yelp Fusion Java Client
<br>

[![release](https://badgen.net/badge/version/1.0.7/green?icon=github)](https://github.com/stewseo/yelp-fusion-client/tree/version-1.0.7)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.stewseo/yelp-fusion-client?versionPrefix=1.0.7)](https://search.maven.org/artifact/io.github.stewseo/yelp-fusion-client/1.0.7/jar)
[![examples](https://badgen.net/badge/docs/examples/cyan?icon=github)](https://stewseo.github.io/yelp-fusion-client/examples)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=stewseo_yelp-fusion-client&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=stewseo_yelp-fusion-client)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=stewseo_yelp-fusion-client&metric=coverage)](https://sonarcloud.io/summary/new_code?id=stewseo_yelp-fusion-client)

### Features based on the [Elasticsearch Java Client](https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/current/introduction.html)
- Create blocking and asynchronous clients with your Yelp Fusion API key.
- Strongly typed requests and responses for all Yelp Fusion APIs.
- Module to handle Http transport including failed request/response logging using Apache Http Components.
- Automatically serializes from and deserializes to application classes using the Jakarta Json Stream API.
- Service interface for Breinify APIs and libraries
[geo json](https://github.com/Breinify/brein-geojson), [time utilities](https://github.com/Breinify/brein-time-utilities), [temporal data](https://docs.breinify.com/?java--native#temporal-data-response)

<br>

### Use cases
- [restaurant recommender](https://github.com/stewseo/restaurant-recommendation-system)

#### As a Maven Dependency
You can use [Maven's dependency management](https://search.maven.org/search?q=g:io.github.stewseo) to obtain the driver by adding the following configuration in the application's Project Object Model (POM) file:

**Example - Maven**
```xml
<dependencies>
<dependency>
  <groupId>io.github.stewseo</groupId>
  <artifactId>yelp-fusion-client</artifactId>
  <version>1.0.75</version>
</dependency>
</dependencies>
```
#### As a Gradle Dependency
You can use [Gradle's dependency management](https://search.maven.org/search?q=g:io.github.stewseo) to obtain the driver by adding the following configuration in the application's ```build.gradle``` file:

**Example - Gradle**
```gradle
dependencies {
   implementation 'io.github.stewseo:yelp-fusion-client:1.0.75'
}
```
<br>


