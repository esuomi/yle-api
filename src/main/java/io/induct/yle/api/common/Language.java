package io.induct.yle.api.common;

/**
 * Country codes used by Yle API.
 *
 * @since 2015-05-17
 * @see <a href="https://en.wikipedia.org/wiki/List_of_ISO_639-2_codes">Wikipedia: ISO-639-x</a>
 */
public enum Language {
    FINNISH("fi"),
    RUSSIAN("ru"),
    ENGLISH("en"),
    SWEDISH("sv"),
    GERMAN("de"),
    ITALIAN("it"),
    FRENCH("fr"),
    NORWEGIAN("no"),
    ESTONIAN("et"),
    DANISH("da"),
    UNKNOWN("unknown");

    private final String languageCode;

    Language(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageCode() {
        return languageCode;
    }
}
