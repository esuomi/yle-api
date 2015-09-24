package io.induct.yle.api.programs.domain.items;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.induct.yle.api.common.Language;
import lombok.Data;

import java.util.List;

/**
 * @since 2015-08-04
 */
@Data
public class Audio {
    private final List<Language> languages;
    private final List<Format> formats;
    private final String type;

    @JsonCreator
    public Audio(@JsonProperty("language") List<Language> languages,
                 @JsonProperty("format") List<Format> formats,
                 @JsonProperty("type") String type) {
        this.languages = languages;
        this.formats = formats;
        this.type = type;
    }

    @Data
    public static class Format {
        private final String inScheme;
        private final String type;
        private final String key;

        @JsonCreator
        public Format(@JsonProperty("inScheme") String inScheme,
                      @JsonProperty("type") String type,
                      @JsonProperty("key") String key) {
            this.inScheme = inScheme;
            this.type = type;
            this.key = key;
        }
    }
}
