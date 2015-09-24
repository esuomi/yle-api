package io.induct.yle.api.programs.domain.items;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.induct.yle.api.common.Language;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Map;

/**
 * @since 2015-05-30
 */
@Data
//@EqualsAndHashCode(callSuper = true)
public class Service {
    public enum Type {
        TV_CHANNEL("tvchannel"),
        RADIO_CHANNEL("radiochannel"),
        ON_DEMAND("ondemandservice"),
        WEBCAST("webcastservice");

        private final String value;

        Type(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }

    @Data
    public static class Homepage {
        private final Map<Language, String> titles;
        private final String type;
        private final String url;
        private final String serviceProvider;

        @JsonCreator
        public Homepage(@JsonProperty("title") Map<Language, String> titles,
                        @JsonProperty("type") String type,
                        @JsonProperty("url") String url,
                        @JsonProperty("serviceProvider") String serviceProvider) {
            this.titles = titles;
            this.type = type;
            this.url = url;
            this.serviceProvider = serviceProvider;
        }

        public String getTitle(Language language) {
            return titles.get(language);
        }

    }

    private final String typeMedia;
    private final Homepage homepage;
    private final DateTime indexDataModified;
    private final String type; // TODO: enum?
    private final List<Interaction> interactions;
    private final Map<Language, String> titles;
    private final String region;
    private final List<ProgramGuide> programGuides;
    private final List<Outlet> outlets;
    private final List<Language> languages;
    private final boolean active;
    private final String id; // this is channel id, not generic YleId
    private final int displayOrder;
    private final String typeCreative;
    private final List<Audio> audios;

    @JsonCreator
    public Service(@JsonProperty("typeMedia") String typeMedia,
                   @JsonProperty("homepage") Homepage homepage,
                   @JsonProperty("indexDataModified") DateTime indexDataModified,
                   @JsonProperty("type") String type,
                   @JsonProperty("interaction") List<Interaction> interactions,
                   @JsonProperty("title") Map<Language, String> titles,
                   @JsonProperty("region") String region,
                   @JsonProperty("programGuide") List<ProgramGuide> programGuides,
                   @JsonProperty("outlets") List<Outlet> outlets,
                   @JsonProperty("language") List<Language> languages,
                   @JsonProperty("active") boolean active,
                   @JsonProperty("id") String id,
                   @JsonProperty("displayOrder") int displayOrder,
                   @JsonProperty("typeCreative") String typeCreative,
                   @JsonProperty("audio") List<Audio> audios) {
        this.typeMedia = typeMedia;
        this.homepage = homepage;
        this.indexDataModified = indexDataModified;
        this.type = type;
        this.interactions = interactions;
        this.titles = titles;
        this.region = region;
        this.programGuides = programGuides;
        this.outlets = outlets;
        this.languages = languages;
        this.active = active;
        this.id = id;
        this.displayOrder = displayOrder;
        this.typeCreative = typeCreative;
        this.audios = audios;
    }

    public String getTitle(Language language) {
        return titles.get(language);
    }

    @Data
    public static class Outlet {
        private final String region;
        private final String type;
        // TODO: media entry for outlet looks like incorrectly serialized ID

        @JsonCreator
        public Outlet(@JsonProperty("region") String region,
                      @JsonProperty("type") String type) {
            this.region = region;
            this.type = type;
        }
    }

    @Data
    public static class ProgramGuide {
        private final Map<Language, String> titles;
        private final String type;
        private final String serviceProvider;
        private final String url;

        @JsonCreator
        public ProgramGuide(@JsonProperty("title") Map<Language, String> titles,
                            @JsonProperty("type") String type,
                            @JsonProperty("url") String url,
                            @JsonProperty("serviceProvider") String serviceProvider) {
            this.titles = titles;
            this.type = type;
            this.serviceProvider = serviceProvider;
            this.url = url;
        }

        public String getTitle(Language language) {
            return titles.get(language);
        }
    }

    @Data
    public static class Interaction {
        private final Map<Language, String> titles;
        private final List<HasVersion> hasVersion;
        private final String type;
        private final String url;
        private final String serviceProvider;

        @JsonCreator
        public Interaction(@JsonProperty("title") Map<Language, String> titles,
                           @JsonProperty("hasVersion") List<HasVersion> hasVersion,
                           @JsonProperty("type") String type,
                           @JsonProperty("url") String url,
                           @JsonProperty("serviceProvider") String serviceProvider) {
        this.titles = titles;
            this.hasVersion = hasVersion;
            this.type = type;
            this.url = url;
            this.serviceProvider = serviceProvider;
        }

        public String getTitle(Language language) {
            return titles.get(language);
        }

        @Data
        public static class HasVersion {
            private final Map<Language, String> titles;
            private final String device;
            private final String type;
            private final String url;
            private final String serviceProvider;

            @JsonCreator
            public HasVersion(@JsonProperty("title") Map<Language, String> titles,
                              @JsonProperty("device") String device,
                              @JsonProperty("type") String type,
                              @JsonProperty("url") String url,
                              @JsonProperty("serviceProvider") String serviceProvider) {
                this.titles = titles;
                this.device = device;
                this.type = type;
                this.url = url;
                this.serviceProvider = serviceProvider;
            }

            public String getTitle(Language language) {
                return titles.get(language);
            }
        }


    }
}
