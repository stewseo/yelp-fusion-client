## The yelp fusion java client provides features based on the [Elasticsearch Java Client](https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/current/introduction.html)
<br>

https://github.com/stewseo/stewseo.github.io

[example requests](https://stewseo.github.io/yelp-fusion-client/examples)

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
    implementation 'io.github.stewseo:yelp-fusion-client:1.0.2'
}
```
Maven
```
<dependency>
    <groupId>io.github.stewseo</groupId>
    <artifactId>yelp-fusion-client</artifactId>
    <version>1.0.2</version>
</dependency>
```
<br>

### End-to-End Test
- Building and submitting a request to the Yelp Fusion Business Search endpoint.
- Parsing the entity's input stream content and mapping json data to a BusinessSearch object
- Indexing the result in Elasticsearch
![Screenshot_20221202_104034](https://user-images.githubusercontent.com/54422342/205407978-447d26e5-940c-4fc6-bbe1-a1f97ad59ef4.png)

<br>

### Terms Aggregation Test

restaurants by category
![Screenshot_20221202_034530](https://user-images.githubusercontent.com/54422342/205410045-91f6fd26-ee69-4fd1-a565-24dc2a791dfd.png)

Top 30 categories, by document(restaurant) count

![Screenshot_20221202_034618](https://user-images.githubusercontent.com/54422342/205410099-4c9a588a-6889-4442-a73a-639c6c69b50c.png)



