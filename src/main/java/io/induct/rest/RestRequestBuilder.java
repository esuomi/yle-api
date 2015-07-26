package io.induct.rest;

import com.google.common.collect.Multimap;

import java.io.InputStream;
import java.util.function.Consumer;

/**
 * @since 2015-05-23
 */
public interface RestRequestBuilder {

    RestRequestBuilder withBaseUrl(String baseUrl);

    RestRequestBuilder withPath(String path);

    RestRequestBuilder withHeaders(Consumer<Multimap<String, String>> contributor);

    RestRequestBuilder withParams(Consumer<Multimap<String, String>> contributor);

    RestRequestBuilder withBody(InputStream body);

    RestRequest build();
}
