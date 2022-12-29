import io.github.stewseo.lowlevel.restclient.PrintUtils;
import io.github.stewseo.lowlevel.restclient.RestClient;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.RequestLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

public class ResponseBodyTest extends LLRestClientTestCase {
    private static final Logger tracer = LoggerFactory.getLogger("tracer");

    @Test
    void responseBodyTest() throws IOException {

        try(RestClient restClient = restClient()) {

            HttpHost host = restClient.getHttpHost();

            HttpRequestBase requestBase = new HttpGet("/v3/businesses/wu3w6IlUct9OvYmYXDMGJA");

            String traceReq = buildTraceRequest(requestBase, host);

            //Process provides control of native processes started by ProcessBuilder.start and Runtime.exec.
            // The class provides methods for performing input from the process, performing output to the process,
            // waiting for the process to complete, checking the exit status of the process, and destroying (killing) the process.
            Process process = Runtime.getRuntime().exec(traceReq);
            assertThat(process.children().count()).isEqualTo(1);
            assertThat(process.descendants().count()).isEqualTo(1);
            assertThat(process.info().user().isPresent()).isTrue();

            try(BufferedReader errorReader = process.errorReader()) {
                if(errorReader.ready()) {
                    tracer.trace(PrintUtils.tracer("errorReader: " + errorReader.readLine()));
                }
            }

            InputStream inputStream = process.getInputStream();

            String resp = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

            assertThat(resp).isNotEmpty();

            inputStream.close();
            process.destroy();
        }

    }

}
