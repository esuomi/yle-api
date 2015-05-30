package io.induct.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.induct.http.Response;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @since 2015-05-23
 */
public class ApiResponse<T> {

    private String apiVersion;
    private Map<String, Object> meta;
    private T data;

    @JsonCreator
    public ApiResponse(@JsonProperty("apiVersion") String apiVersion,
                       @JsonProperty("meta") Map<String, Object> meta,
                       @JsonProperty("data") T data) {
        this.apiVersion = apiVersion;
        this.meta = meta;
        this.data = data;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public Map<String, Object> getMeta() {
        return meta;
    }

    public T getData() {
        return data;
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
            return null;//new ApiResponse<>(statusCode, body);
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
