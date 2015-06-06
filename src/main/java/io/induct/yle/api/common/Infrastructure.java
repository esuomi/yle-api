package io.induct.yle.api.common;

import com.google.common.util.concurrent.RateLimiter;
import io.induct.http.HttpClient;
import io.induct.rest.DefaultRequestBuilder;
import io.induct.rest.RequestBuilder;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @since 2015-05-17
 */
// now ain't this a terrible name for a class?
public class Infrastructure {
    private final HttpClient httpClient;
    private final String appId;
    private final String appKey;

    @Inject
    public Infrastructure(HttpClient httpClient,
                          @Named("yle.api.appId") String appId,
                          @Named("yle.api.appKey") String appKey) {
        this.httpClient = httpClient;
        this.appId = appId;
        this.appKey = appKey;
    }

    public RequestBuilder createRequestBuilder(String baseUrl, RateLimiter rateLimiter) {
        return new RateLimitingRequestBuilder(rateLimiter, new DefaultRequestBuilder(httpClient)
                .withBaseUrl(baseUrl)
                .withParams(params -> {
                    params.put("app_id", appId);
                    params.put("app_key", appKey);
                }));
    }

}
