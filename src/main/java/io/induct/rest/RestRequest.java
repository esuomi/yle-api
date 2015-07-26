package io.induct.rest;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import io.induct.http.HttpClient;
import io.induct.http.Response;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.function.Consumer;

/**
 * @since 2015-05-09
 */
public class RestRequest {

    private final HttpClient http;
    private final String url;
    private final Multimap<String, String> headers;
    private final Multimap<String, String> params;
    private final InputStream body;

    public RestRequest(HttpClient http, String url, Multimap<String, String> headers, Multimap<String, String> params, InputStream body) {
        this.http = http;
        this.url = url;
        this.headers = headers;
        this.params = params;
        this.body = body;
    }

    public Response get() {
        return call(http::get);
    }

    public Response post() {
        return call(http::post);
    }

    public Response put() {
        return call(http::put);
    }

    public Response delete() {
        return call(http::delete);
    }

    public HttpClient getHttp() {
        return http;
    }

    public String getUrl() {
        return url;
    }

    public Multimap<String, String> getHeaders() {
        return headers;
    }

    public Multimap<String, String> getParams() {
        return params;
    }

    public InputStream getBody() {
        return body;
    }

    protected Response call(Method m) {
        return m.call(url, params, headers, body);
    }

    protected interface Method {
        Response call(String url, Multimap<String, String> params, Multimap<String, String> headers, InputStream body);
    }

    /**
     * @since 2015-05-09
     */
    public static class Builder implements RestRequestBuilder {

        private final HttpClient httpClient;
        private String baseUrl;
        private String path;

        private Multimap<String, String> headers = MultimapBuilder.hashKeys().arrayListValues().build();

        private Multimap<String, String> params = MultimapBuilder.hashKeys().arrayListValues().build();

        private InputStream body = new ByteArrayInputStream(new byte[0]);

        public Builder(HttpClient httpClient) {
            this.httpClient = httpClient;
        }

        @Override
        public Builder withBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        @Override
        public Builder withPath(String path) {
            this.path = path;
            return this;
        }

        @Override
        public Builder withHeaders(Consumer<Multimap<String, String>> contributor) {
            contributor.accept(headers);
            return this;
        }

        @Override
        public Builder withParams(Consumer<Multimap<String, String>> contributor) {
            contributor.accept(params);
            return this;
        }

        @Override
        public Builder withBody(InputStream body) {
            this.body = body;
            return this;
        }

        @Override
        public RestRequest build() {
            // TODO: Validate url+path
            return new RestRequest(httpClient, baseUrl + path, headers, params, body);
        }
    }
}
