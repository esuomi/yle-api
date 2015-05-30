package io.induct.rest;

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
}
