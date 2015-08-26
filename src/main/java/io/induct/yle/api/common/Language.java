package io.induct.yle.api.common;

/**
 * @since 2015-05-17
 */
public enum Language {
    FINNISH("fi"),
    RUSSIAN("ru"),
    ENGLISH("en"),
    SWEDISH("sv"),
    UNKNOWN("unknown");

    private final String languageCode;

    Language(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageCode() {
        return languageCode;
    }
}
