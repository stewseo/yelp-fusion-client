package io.github.lowlevel.restclient;

import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicRequestLine;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("ParameterCanBeLocal")
public class Response {

    private RequestLine requestLine;
    private HttpHost host;
    private HttpResponse response;
    java.net.http.HttpRequest jdkRequest;

    java.net.http.HttpResponse<String> jdkResponse;

    public Response(java.net.http.HttpRequest jdkHttpRequest, HttpClient jdkHttpClient) {
        this.jdkRequest = jdkHttpRequest;

        this.requestLine = new BasicRequestLine("Get", jdkHttpClient.toString(), new ProtocolVersion("http",1,1));

        this.host = new HttpHost("api.yelp.com", 443, "https");

        response = null;
    }

    @SuppressWarnings("ParameterCanBeLocal")
    public Response(java.net.http.HttpResponse<InputStream> httpResponse, HttpHost host) {
        host = new HttpHost("api.yelp.com", 443, "http");
    }

    public Response(java.net.http.HttpRequest jdkHttpRequest, URI requestLine) {

    }


    public java.net.http.HttpResponse<String> jdkHttpResponse() {
        return jdkHttpResponse;
    }

    private java.net.http.HttpResponse<String> jdkHttpResponse;

    public Response(RequestLine requestLine, HttpHost host, HttpResponse response) {

        this.response = response;

        this.requestLine = new BasicRequestLine("Get", "uri", new ProtocolVersion("http",1,1));

        this.host = new HttpHost("api.yelp.com", 443, "https");

    }

    public Response(HttpHost host, java.net.http.HttpResponse<String> jdkHttpResponse) {

        this.host = host;
        this.jdkHttpResponse = jdkHttpResponse;
        String reqLine = jdkHttpResponse.request().method() + jdkHttpResponse.request().uri().toString();
        HttpGet apacheReq = new HttpGet(reqLine);
        this.requestLine = apacheReq.getRequestLine();

        this.response = null;
    }


    public java.net.http.HttpRequest getJdkRequest() {
        return jdkRequest;
    }

    public java.net.http.HttpResponse<String> getJdkHttpResponse() {
        return jdkHttpResponse;
    }

    public RequestLine getRequestLine() {
        return requestLine;
    }


    public HttpHost getHost() {
        return host;
    }

    public StatusLine getStatusLine() {
        return response.getStatusLine();
    }

    public Header[] getHeaders() {
        return response.getAllHeaders();
    }


    public String getHeader(String name) {
        Header header = response.getFirstHeader(name);
        if (header == null) {
            return null;
        }
        return header.getValue();
    }

    /**
     * Returns the response body available, null otherwise
     * @see HttpEntity
     */
    public HttpEntity getEntity() {
        return response.getEntity();
    }

    /**
     * Optimized regular expression to test if a string matches the RFC 1123 date
     * format (with quotes and leading space). Start/end of line characters and
     * atomic groups are used to prevent backtracking.
     */
    private static final Pattern WARNING_HEADER_DATE_PATTERN = Pattern.compile("^ " + // start of line, leading space
            // quoted RFC 1123 date format
            "\"" + // opening quote
            "(?>Mon|Tue|Wed|Thu|Fri|Sat|Sun), " + // day of week, atomic group to prevent backtracking
            "\\d{2} " + // 2-digit day
            "(?>Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) " + // month, atomic group to prevent backtracking
            "\\d{4} " + // 4-digit year
            "\\d{2}:\\d{2}:\\d{2} " + // (two-digit hour):(two-digit minute):(two-digit second)
            "GMT" + // GMT
            "\"$"); // closing quote (optional, since an older version can still send a warn-date), end of line

    /**
     * Length of RFC 1123 format (with quotes and leading space), used in
     * matchWarningHeaderPatternByPrefix(String).
     */
    // tag::noformat
    private static final int WARNING_HEADER_DATE_LENGTH = 0
            + 1
            + 1
            + 3 + 1 + 1
            + 2 + 1
            + 3 + 1
            + 4 + 1
            + 2 + 1 + 2 + 1 + 2 + 1
            + 3
            + 1;
    // end::noformat

    /**
     * Tests if a string matches the RFC 7234 specification for warning headers.
     * This assumes that the warn code is always 299 or 300 and the warn agent is
     * always Elasticsearch.
     *
     * @param s the value of a warning header formatted according to RFC 7234
     * @return {@code true} if the input string matches the specification
     */
    private static boolean matchWarningHeaderPatternByPrefix(final String s) {
        return s.startsWith("299 Elasticsearch-") || s.startsWith("300 Elasticsearch-");
    }

    /**
     * Refer to org.elasticsearch.common.logging.DeprecationLogger
     */
    private static String extractWarningValueFromWarningHeader(final String s) {
        String warningHeader = s;

        /*
         * The following block tests for the existence of a RFC 1123 date in the warning header. If the date exists, it is removed for
         * extractWarningValueFromWarningHeader(String) to work properly (as it does not handle dates).
         */
        if (s.length() > WARNING_HEADER_DATE_LENGTH) {
            final String possibleDateString = s.substring(s.length() - WARNING_HEADER_DATE_LENGTH);
            final Matcher matcher = WARNING_HEADER_DATE_PATTERN.matcher(possibleDateString);

            if (matcher.matches()) {
                warningHeader = warningHeader.substring(0, s.length() - WARNING_HEADER_DATE_LENGTH);
            }
        }

        final int firstQuote = warningHeader.indexOf('\"');
        final int lastQuote = warningHeader.length() - 1;
        final String warningValue = warningHeader.substring(firstQuote + 1, lastQuote);
        return warningValue;
    }

    /**
     * Returns a list of all warning headers returned in the response.
     */
    public List<String> getWarnings() {

        if (jdkHttpResponse != null) {
            List<String> warnings = jdkHttpResponse.headers().map().get("Warning");
            if (warnings != null) {
                warnings.forEach(warning -> {
                    if (matchWarningHeaderPatternByPrefix(warning)) {
                        warnings.add(extractWarningValueFromWarningHeader(warning));
                    } else {
                        warnings.add(warning);
                    }
                });

            }
            return warnings;
        }
        else {
            List<String> warnings = new ArrayList<>();
            for (Header header : response.getHeaders("Warning")) {
                String warning = header.getValue();
                if (matchWarningHeaderPatternByPrefix(warning)) {
                    warnings.add(extractWarningValueFromWarningHeader(warning));
                } else {
                    warnings.add(warning);
                }
            }
            return warnings;
        }
    }

    /**
     * Returns true if there is at least one warning header returned in the
     * response.
     */
    public boolean hasWarnings() {
        if(jdkHttpResponse != null) {
            java.net.http.HttpHeaders headers = jdkHttpResponse.headers();
            List<String> warning = headers.map().get("Warning");
            return warning != null && warning.size() > 0;
        }else {
            Header[] warnings = response.getHeaders("Warning");
            return warnings != null && warnings.length > 0;
        }
    }

    public HttpResponse getHttpResponse() {
        return response;
    }

    @Override
    public String toString() {
        String statusLine = getStatusLine() == null ? "null" : response.getStatusLine().toString();

        return "Response{" + "requestLine=" + requestLine + ", host=" + host + ", response=" + statusLine + '}';
    }
}

