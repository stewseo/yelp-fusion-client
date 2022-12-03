package io.github.stewseo.yelp.fusion.client.json;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsonpDeserializable {
    
    String field() default "_DESERIALIZER";
}