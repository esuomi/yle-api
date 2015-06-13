package io.induct.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

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

}
