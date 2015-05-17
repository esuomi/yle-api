package io.induct.yle.api.common;

/**
 * @since 2015-05-17
 */
public enum Language {
    FINNISH("fi"),
    SAMI("sv"),
    RUSSIAN("ru"),
    ENGLISH("en"),
    SWEDISH("se");

    private final String languageCode;

    Language(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageCode() {
        return languageCode;
    }
}
