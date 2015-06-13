package io.induct.yle.api.common;

/**
 * @since 2015-06-13
 */
public enum MediaObject {

    AUDIO("audio"),
    VIDEO("video");

    private final String apiParamName;

    MediaObject(String apiParamName) {
        this.apiParamName = apiParamName;
    }

    public String getApiParamName() {
        return apiParamName;
    }
}
