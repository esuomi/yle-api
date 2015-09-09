package io.induct.yle.cdn.images.domain;

/**
 * @since 2015-09-09
 */
public enum Format {
    JPEG("jpg"),
    PNG("png"),
    GIF("gif");

    private final String apiValue;

    Format(String apiValue) {
        this.apiValue = apiValue;
    }

    public String getApiValue() {
        return apiValue;
    }
}
