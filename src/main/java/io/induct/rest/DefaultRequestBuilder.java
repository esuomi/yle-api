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
public class DefaultRequestBuilder implements RequestBuilder {

    private final HttpClient httpClient;
    private String baseUrl;
    private String path;

    private Multimap<String, String> headers = MultimapBuilder.hashKeys().arrayListValues().build();

    private Multimap<String, String> params = MultimapBuilder.hashKeys().arrayListValues().build();

    private InputStream body = new ByteArrayInputStream(new byte[0]);

    public DefaultRequestBuilder(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public DefaultRequestBuilder withBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    @Override
    public DefaultRequestBuilder withPath(String path) {
        this.path = path;
        return this;
    }

    @Override
    public DefaultRequestBuilder withHeaders(Consumer<Multimap<String, String>> contributor) {
        contributor.accept(headers);
        return this;
    }

    @Override
    public DefaultRequestBuilder withParams(Consumer<Multimap<String, String>> contributor) {
        contributor.accept(params);
        return this;
    }

    @Override
    public DefaultRequestBuilder withBody(InputStream body) {
        this.body = body;
        return this;
    }

    @Override
    public Request build() {
        // TODO: Validate url+path
        return new Request(httpClient, baseUrl + path, headers, params, body);
    }
}
