package io.induct.yle.api.common;

import com.google.common.collect.Multimap;
import com.google.common.util.concurrent.RateLimiter;
import io.induct.http.Response;
import io.induct.rest.RestRequest;
import io.induct.rest.RestRequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.function.Consumer;

/**
 * @since 2015-05-23
 */
public class RateLimitingRestRequest extends RestRequest {

    private final Logger log = LoggerFactory.getLogger(RateLimitingRestRequest.class);

    private final RateLimiter rateLimiter;

    public RateLimitingRestRequest(RateLimiter rateLimiter, RestRequest restRequest) {
        super(restRequest.getHttp(), restRequest.getUrl(), restRequest.getHeaders(), restRequest.getParams(), restRequest.getBody());
        this.rateLimiter = rateLimiter;
    }

    @Override
    protected Response call(RestRequest.Method m) {
        double delayed = rateLimiter.acquire();
        if (delayed > 0.0) {
            log.debug("Rate limit exceeded, call delayed by {} seconds", delayed);
        }
        return super.call(m);
    }

    /**
     * @since 2015-05-23
     */
    public static class Builder implements RestRequestBuilder {
        private final RateLimiter rateLimiter;
        private final RestRequestBuilder delegate;

        public Builder(RateLimiter rateLimiter,
                       RestRequestBuilder delegate) {
            this.rateLimiter = rateLimiter;
            this.delegate = delegate;
        }

        @Override
        public RestRequestBuilder withBaseUrl(String baseUrl) {
            delegate.withBaseUrl(baseUrl);
            return this;
        }

        @Override
        public RestRequestBuilder withPath(String path) {
            delegate.withPath(path);
            return this;
        }

        @Override
        public RestRequestBuilder withHeaders(Consumer<Multimap<String, String>> contributor) {
            delegate.withHeaders(contributor);
            return this;
        }

        @Override
        public RestRequestBuilder withParams(Consumer<Multimap<String, String>> contributor) {
            delegate.withParams(contributor);
            return this;
        }

        @Override
        public RestRequestBuilder withBody(InputStream body) {
            delegate.withBody(body);
            return this;
        }

        @Override
        public RestRequest build() {
            return new RateLimitingRestRequest(rateLimiter, delegate.build());
        }
    }
}
