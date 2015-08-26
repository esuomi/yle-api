package io.induct.yle.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.CharStreams;
import com.google.common.util.concurrent.RateLimiter;
import io.induct.daniel.Daniel;
import io.induct.http.Response;
import io.induct.rest.ApiResponse;
import io.induct.rest.Request;
import io.induct.rest.RequestBuilder;
import io.induct.yle.api.common.Infrastructure;
import io.induct.yle.api.exceptions.InvalidParametersException;
import io.induct.yle.api.exceptions.NotFoundException;
import io.induct.yle.api.exceptions.UnauthorizedException;
import io.induct.yle.api.exceptions.UnknownResponseCodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * <code>StandardizedApi</code> describes all the traits common to all Yle APIs such as error handling.
 *
 * @since 2015-08-25
 */
public abstract class StandardizedApi {

    private final Logger log = LoggerFactory.getLogger(StandardizedApi.class);

    private final String baseUrl;

    @Inject
    @Named("yle.api.rateLimit")
    private RateLimiter rateLimiter;

    @Inject
    private Infrastructure infrastructure;

    @Inject
    private Daniel daniel;

    public StandardizedApi(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    protected <T> ApiResponse<T> handleApiCall(Request request, TypeReference<ApiResponse<T>> targetType) {
        try (Response response = request.get()) {

            switch (response.getStatusCode()) {
                // informational:
                // success:
                case 200: {
                    Optional<InputStream> body = response.getResponseBody();
                    if (body.isPresent()) {
                        log.debug("Body is present");
                        if (targetType != null) {
                            return daniel.deserialize(targetType, response.getResponseBody().get());
                        } else {
                            // TODO: This is debugging branch and should not be present in final code!
                            InputStream resp = response.getResponseBody().get();
                            String content = null;
                            try {
                                content = CharStreams.toString(new InputStreamReader(resp));
                            } catch (IOException e) {
                                log.error("Failed to print stream", e);
                            }
                            log.info("content: {}", content);
                            return null;
                        }
                    } else {
                        log.debug("Body is absent, returning empty ApiResponse");
                        return new ApiResponse<>("unknown", ImmutableMap.of(), null);
                    }
                }
                // redirect:
                // client error:
                case 400: throw new InvalidParametersException();
                case 401: throw new UnauthorizedException();
                case 404: throw new NotFoundException();
                    // server error:
                default: throw new UnknownResponseCodeException(response.getStatusCode());// !?
            }
        } finally {
            log.info("Done handling call to {}", request.getUrl());
        }
    }

    protected RequestBuilder createRequestBuilder() {
        return infrastructure.createRequestBuilder(baseUrl, rateLimiter);
    }

}
