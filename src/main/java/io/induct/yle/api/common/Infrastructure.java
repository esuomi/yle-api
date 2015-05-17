package io.induct.yle.api.common;

import io.induct.http.HttpClient;
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

    public RequestBuilder createRequestBuilder() {
        return new RequestBuilder(httpClient)
                .withParams(params -> {
                    params.put("app_id", appId);
                    params.put("app_key", appKey);
                });
    }

}
