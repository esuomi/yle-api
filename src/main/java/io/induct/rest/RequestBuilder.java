package io.induct.rest;

import com.google.common.collect.Multimap;

import java.io.InputStream;
import java.util.function.Consumer;

/**
 * @since 2015-05-23
 */
public interface RequestBuilder {

    RequestBuilder withBaseUrl(String baseUrl);

    RequestBuilder withPath(String path);

    RequestBuilder withHeaders(Consumer<Multimap<String, String>> contributor);

    RequestBuilder withParams(Consumer<Multimap<String, String>> contributor);

    RequestBuilder withBody(InputStream body);

    Request build();
}
