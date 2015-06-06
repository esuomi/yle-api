package io.induct.rest;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import io.induct.http.HttpClient;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.function.Consumer;

/**
 * @since 2015-05-09
 */
public class RequestBuilder {

    private final HttpClient httpClient;
    private String baseUrl;
    private String path;

    private Multimap<String, String> headers = MultimapBuilder.hashKeys().arrayListValues().build();

    private Multimap<String, String> params = MultimapBuilder.hashKeys().arrayListValues().build();

    private InputStream body = new ByteArrayInputStream(new byte[0]);

    public RequestBuilder(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public RequestBuilder withBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public RequestBuilder withPath(String path) {
        this.path = path;
        return this;
    }

    public RequestBuilder withHeaders(Consumer<Multimap<String, String>> contributor) {
        contributor.accept(headers);
        return this;
    }

    public RequestBuilder withParams(Consumer<Multimap<String, String>> contributor) {
        contributor.accept(params);
        return this;
    }

    public RequestBuilder withBody(InputStream body) {
        this.body = body;
        return this;
    }

    public Request build() {
        // TODO: Validate url+path
        return new Request(httpClient, baseUrl + path, headers, params, body);
    }
}
