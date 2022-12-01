/*
 * Licensed to Elasticsearch B.V. under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch B.V. licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.github.yelp.fusion.restclient;

import org.apache.http.Header;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.message.BasicHeader;

import java.util.*;


public final class RequestOptions {
    /**
     * Default request options.
     */
    public static final RequestOptions DEFAULT = new Builder(
            Collections.emptyList(),
            Collections.emptyMap(),
            HttpAsyncResponseConsumerFactory.HeapBufferedResponseConsumerFactory.DEFAULT,
            null,
            null
    ).build();

    private final List<Header> headers;

    private final Map<String, String> parameters;

    private final HttpAsyncResponseConsumerFactory httpAsyncResponseConsumerFactory;

    private final WarningsHandler warningsHandler;

    private final RequestConfig requestConfig;

    private RequestOptions(Builder builder) {
        this.headers = List.copyOf(builder.headers);
        this.parameters = Collections.unmodifiableMap(builder.parameters);
        this.httpAsyncResponseConsumerFactory = builder.httpAsyncResponseConsumerFactory;
        this.warningsHandler = builder.warningsHandler;
        this.requestConfig = builder.requestConfig;
    }


    /**
     * Create a builder that contains these options but can be modified.
     */
    public Builder toBuilder() {
        return new Builder(headers, parameters, httpAsyncResponseConsumerFactory, warningsHandler, requestConfig);
    }


    /**
     * Headers to attach to the request.
     */
    public List<Header> getHeaders() {
        return headers;
    }


    /**
     * Return true if the options contain the given header
     */
    public boolean containsHeader(String name) {
        return headers.stream().anyMatch(h -> name.equalsIgnoreCase(h.getName()));
    }


    public Map<String, String> getParameters() {
        return parameters;
    }

    public HttpAsyncResponseConsumerFactory getHttpAsyncResponseConsumerFactory() {
        return httpAsyncResponseConsumerFactory;
    }

    public WarningsHandler getWarningsHandler() {
        return warningsHandler;
    }


    /**
     * get RequestConfig, which can set socketTimeout, connectTimeout
     * and so on by request
     * @return RequestConfig
     */
    public RequestConfig getRequestConfig() {
        return requestConfig;
    }


    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("RequestOptions{");
        boolean comma = false;
        if (headers.size() > 0) {
            b.append("headers=");
            comma = true;
            for (int h = 0; h < headers.size(); h++) {
                if (h != 0) {
                    b.append(',');
                }
                b.append(headers.get(h).toString());
            }
        }
        if (httpAsyncResponseConsumerFactory != HttpAsyncResponseConsumerFactory.DEFAULT) {
            if (comma) b.append(", ");
            comma = true;
            b.append("consumerFactory=").append(httpAsyncResponseConsumerFactory);
        }
        if (warningsHandler != null) {
            if (comma) b.append(", ");
            b.append("warningsHandler=").append(warningsHandler);
        }
        return b.append('}').toString();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null || (obj.getClass() != getClass())) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        RequestOptions other = (RequestOptions) obj;
        return headers.equals(other.headers)
                && httpAsyncResponseConsumerFactory.equals(other.httpAsyncResponseConsumerFactory)
                && Objects.equals(warningsHandler, other.warningsHandler);
    }


    @Override
    public int hashCode() {
        return Objects.hash(headers, httpAsyncResponseConsumerFactory, warningsHandler);
    }


    /**
     * Builds {@link RequestOptions}. Get one by calling
     * {@link RequestOptions#toBuilder} on {@link RequestOptions#DEFAULT} or
     * any other {@linkplain RequestOptions}.
     */
    public static class Builder {
        private final List<Header> headers;
        private final Map<String, String> parameters;
        private HttpAsyncResponseConsumerFactory httpAsyncResponseConsumerFactory;
        private WarningsHandler warningsHandler;
        private RequestConfig requestConfig;

        private Builder(
                List<Header> headers,
                Map<String, String> parameters,
                HttpAsyncResponseConsumerFactory httpAsyncResponseConsumerFactory,
                WarningsHandler warningsHandler,
                RequestConfig requestConfig
        ) {
            this.headers = new ArrayList<>(headers);
            this.parameters = new HashMap<>(parameters);
            this.httpAsyncResponseConsumerFactory = httpAsyncResponseConsumerFactory;
            this.warningsHandler = warningsHandler;
            this.requestConfig = requestConfig;
        }

        /**
         * Build the {@linkplain RequestOptions}.
         */
        public RequestOptions build() {
            return new RequestOptions(this);
        }

        /**
         * Add the provided header to the request.
         */
        public Builder addHeader(String name, String value) {
            Objects.requireNonNull(name, "header name cannot be null");
            Objects.requireNonNull(value, "header value cannot be null");
            this.headers.add(new ReqHeader(name, value));
            return this;
        }

        /**
         * Remove all headers with the given name.
         */
        public Builder removeHeader(String name) {
            Objects.requireNonNull(name, "header name cannot be null");
            this.headers.removeIf(h -> name.equalsIgnoreCase(h.getName()));
            return this;
        }

        /**
         * Return all headers for the request
         */
        public List<Header> getHeaders() {
            return this.headers;
        }


        public Builder addParameter(String key, String value) {
            Objects.requireNonNull(key, "parameter key cannot be null");
            Objects.requireNonNull(value, "parameter value cannot be null");
            this.parameters.merge(key, value, (existingValue, newValue) -> String.join(",", existingValue, newValue));
            return this;
        }


        public Builder setHttpAsyncResponseConsumerFactory(HttpAsyncResponseConsumerFactory httpAsyncResponseConsumerFactory) {
            this.httpAsyncResponseConsumerFactory = Objects.requireNonNull(
                    httpAsyncResponseConsumerFactory,
                    "httpAsyncResponseConsumerFactory cannot be null"
            );
            return this;
        }


        public Builder setWarningsHandler(WarningsHandler warningsHandler) {
            this.warningsHandler = warningsHandler;
            return this;
        }

        /**
         * set RequestConfig, which can set socketTimeout, connectTimeout
         * and so on by request
         * @param requestConfig http client RequestConfig
         * @return Builder
         */
        public Builder setRequestConfig(RequestConfig requestConfig) {
            this.requestConfig = requestConfig;
            return this;
        }
    }

    /**
     * Custom implementation of {@link BasicHeader} that overrides equals and
     * hashCode, so it is easier to test equality of {@link RequestOptions}.
     */
    static final class ReqHeader extends BasicHeader {

        ReqHeader(String name, String value) {
            super(name, value);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other instanceof ReqHeader) {
                Header otherHeader = (Header) other;
                return Objects.equals(getName(), otherHeader.getName()) && Objects.equals(getValue(), otherHeader.getValue());
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(getName(), getValue());
        }
    }
}
