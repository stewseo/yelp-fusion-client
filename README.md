### yelp-fusion-java-client provides an up-to-date Java client based on the [Elasticsearch Java Client](https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/current/introduction.html)
- Build objects for Yelp Fusion APIs using blocking and asynchronous API clients.
- Http protocol handling is provided based on the [Elasticsearch Java Low Level Client](https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/current/java-rest-low.html)
- Automatically maps json to/from an application class.

Gradle Configuration
```
dependencies {
    implementation 'io.github.stewseo:yelp-fusion-client:1.0.0'
}
```
Maven Configuration
```
<dependency>
    <groupId>io.github.stewseo</groupId>
    <artifactId>yelp-fusion-client</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Testing a request to the Yelp Fusion Business Search endpoint and indexing the deserialized response in an Elasticsearch deployment
![Screenshot_20221201_092328](https://user-images.githubusercontent.com/54422342/205120156-f1bee922-204c-410a-bec1-8dd4f935ae4b.png)
