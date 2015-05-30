package io.induct.yle.api.programs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.util.concurrent.RateLimiter;
import io.induct.daniel.Daniel;
import io.induct.rest.ApiResponse;
import io.induct.rest.Request;
import io.induct.rest.RequestBuilder;
import io.induct.yle.api.common.Infrastructure;
import io.induct.yle.api.programs.model.Service;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @since 2015-05-30
 */
public class YleProgramsApi {

    public static final String PROFILES_BASE_URL = "https://profiles.api.yle.fi";

    private final TypeReference<List<Service>> listOfServices;

    private final RateLimiter rateLimiter;
    private final Infrastructure infrastructure;
    private final Daniel daniel;

    @Inject
    public YleProgramsApi(@Named("yle.api.rateLimit") RateLimiter rateLimiter,
                          Infrastructure infrastructure,
                          Daniel daniel) {
        this.listOfServices = new TypeReference<List<Service>>() {};
        this.rateLimiter = rateLimiter;
        this.infrastructure = infrastructure;
        this.daniel = daniel;
    }

    public ApiResponse<List<Service>> listServices(Service.Type type, int limit, int offset) {
        Request request = createRequestBuilder()
                .withPath("/v1/services.json")
                .withParams(params -> {
                    params.put("type", type.value());
                    params.put("limit", Integer.toString(limit));
                    params.put("offset", Integer.toString(offset));
                })
                .build();

        return ApiResponse.map(
                request::get,
                response -> daniel.deserializeAll(listOfServices, response.getResponseBody().get()));
    }

    private RequestBuilder createRequestBuilder() {
        return infrastructure.createRequestBuilder(PROFILES_BASE_URL, rateLimiter);
    }
}
