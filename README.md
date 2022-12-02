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
Gradle
```
dependencies {
    implementation 'io.github.stewseo:yelp-fusion-client:1.0.0'
}
```
Maven
```
<dependency>
    <groupId>io.github.stewseo</groupId>
    <artifactId>yelp-fusion-client</artifactId>
    <version>1.0.0</version>
</dependency>
```
<br>

### End-to-End Test
- Building and submitting a request to the Yelp Fusion Business Search endpoint.
- Parsing the entity's input stream content and mapping json data to a BusinessSearch object
- Indexing the result in Elasticsearch
  ![Screenshot_20221201_092328](https://user-images.githubusercontent.com/54422342/205120156-f1bee922-204c-410a-bec1-8dd4f935ae4b.png)

<br>

### Terms Aggregation Test
restaurants by category
