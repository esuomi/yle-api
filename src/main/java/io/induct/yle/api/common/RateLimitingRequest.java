package io.induct.yle.api.common;

import com.google.common.util.concurrent.RateLimiter;
import io.induct.http.Response;
import io.induct.rest.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @since 2015-05-23
 */
public class RateLimitingRequest extends Request {

    private final Logger log = LoggerFactory.getLogger(RateLimitingRequest.class);

    private final RateLimiter rateLimiter;

    public RateLimitingRequest(RateLimiter rateLimiter, Request request) {
        super(request.getHttp(), request.getUrl(), request.getHeaders(), request.getParams(), request.getBody());
        this.rateLimiter = rateLimiter;
    }

    @Override
    protected Response call(Request.Method m) {
        double delayed = rateLimiter.acquire();
        if (delayed > 0.0) {
            log.debug("Rate limit exceeded, call delayed by {} seconds", delayed);
        }
        return super.call(m);
    }
}
