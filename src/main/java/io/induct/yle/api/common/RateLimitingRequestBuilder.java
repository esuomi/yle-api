package io.induct.yle.api.common;

import com.google.common.collect.Multimap;
import com.google.common.util.concurrent.RateLimiter;
import io.induct.rest.Request;
import io.induct.rest.RequestBuilder;

import java.io.InputStream;
import java.util.function.Consumer;

/**
 * @since 2015-05-23
 */
public class RateLimitingRequestBuilder implements RequestBuilder {
    private final RateLimiter rateLimiter;
    private final RequestBuilder delegate;

    public RateLimitingRequestBuilder(RateLimiter rateLimiter,
                                      RequestBuilder delegate) {
        this.rateLimiter = rateLimiter;
        this.delegate = delegate;
    }

    @Override
    public RequestBuilder withBaseUrl(String baseUrl) {
        delegate.withBaseUrl(baseUrl);
        return this;
    }

    @Override
    public RequestBuilder withPath(String path) {
        delegate.withPath(path);
        return this;
    }

    @Override
    public RequestBuilder withHeaders(Consumer<Multimap<String, String>> contributor) {
        delegate.withHeaders(contributor);
        return this;
    }

    @Override
    public RequestBuilder withParams(Consumer<Multimap<String, String>> contributor) {
        delegate.withParams(contributor);
        return this;
    }

    @Override
    public RequestBuilder withBody(InputStream body) {
        delegate.withBody(body);
        return this;
    }

    @Override
    public Request build() {
        return new RateLimitingRequest(rateLimiter, delegate.build());
    }
}
