package io.induct.rest;

import io.induct.http.Response;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @since 2015-05-23
 */
public class ApiResponse<T> {

    private final int statusCode;
    private final T body;

    public ApiResponse(int statusCode, T body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public T getBody() {
        return body;
    }

    public static class Builder<T> {
        private int statusCode;
        private T body;

        public Builder<T> withStatus(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }
        public Builder<T> withBody(T body) {
            this.body = body;
            return this;
        }

        public ApiResponse<T> build() {
            return new ApiResponse<>(statusCode, body);
        }
    }

    public static <C> ApiResponse<C> map(Supplier<Response> request, Function<Response, C> bodyExtractor) {
        Response response = request.get();
        ApiResponse.Builder<C> responseBuilder = new ApiResponse.Builder<>();
        responseBuilder.withStatus(response.getStatusCode());
        if (response.getResponseBody().isPresent()) {
            responseBuilder.withBody(bodyExtractor.apply(response));
        }
        return responseBuilder.build();
    }

}
