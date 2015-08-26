package io.induct.http.testing;

import com.google.common.base.Optional;
import com.google.common.collect.Multimap;
import io.induct.http.Response;

import java.io.InputStream;

/**
 * @since 2015-08-04
 */
public class StaticResponse implements Response {
    private final int statusCode;
    private final Multimap<String, String> responseHeaders;
    private final Optional<InputStream> responseBody;

    public StaticResponse(int statusCode, Multimap<String, String> responseHeaders, Optional<InputStream> responseBody) {
        this.statusCode = statusCode;
        this.responseHeaders = responseHeaders;
        this.responseBody = responseBody;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public Multimap<String, String> getResponseHeaders() {
        return responseHeaders;
    }

    @Override
    public Optional<InputStream> getResponseBody() {
        return responseBody;
    }

    @Override
    public void close() {

    }
}
