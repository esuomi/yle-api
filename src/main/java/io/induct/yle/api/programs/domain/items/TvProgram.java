package io.induct.yle.api.programs.domain.items;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;
import io.induct.domain.Entity;
import io.induct.yle.api.YleId;
import io.induct.yle.api.common.Language;
import io.induct.yle.api.programs.domain.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @since 2015-05-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TvProgram extends Item {
    private final Map<Language, String> descriptions;
    private final Video video;
    private final String typeMedia;
    private final List<Creator> creators;
    private final int episodeNumber;
    private final Map<Language, String> promotionTitles;
    private final DateTime indexDataModified;
    private final Season season;
    private final Series series;
    private final List<YleId> alternativeIds;
    private final String duration; // TODO: looks like custom format, objectify
    private final String productionId;
    private final ContentRating contentRating;
    private final Map<Language, String> titles;
    //private final Map<Language, String> itemTitles;
    private final List<String> countryOfOrigin;
    private final String typeCreative;
    private final Image image;
    private final List<Audio> audio;
    private final Map<Language, String> originalTitles;
    private final List<PublicationEvent> publicationEvents;
    private final String collection;
    private final List<Subject> subject;
    private final List<Subtitling> subtitling;

    @JsonCreator
    public TvProgram(@JsonProperty("id") YleId identity,
                     @JsonProperty("description") Map<Language, String> descriptions,
                     @JsonProperty("creator") List<Creator> creators,
                     @JsonProperty("partOfSeason") Season season,
                     @JsonProperty("partOfSeries") Series series,
                     @JsonProperty("video") Video video,
                     @JsonProperty("typeMedia") String typeMedia,
                     @JsonProperty("episodeNumber") int episodeNumber,
                     @JsonProperty("promotionTitle") Map<Language, String> promotionTitles,
                     @JsonProperty("indexDataModified") DateTime indexDataModified,
                     @JsonProperty("alternativeId") List<YleId> alternativeIds,
                     @JsonProperty("duration") String duration,
                     @JsonProperty("productionId") String productionId,
                     @JsonProperty("contentRating") ContentRating contentRating,
                     @JsonProperty("title") Map<Language, String> title,
                     //@JsonProperty("itemTitle") Map<Language, String> itemTitles, TODO: content currently unknown
                     @JsonProperty("countryOfOrigin") List<String> countryOfOrigin,
                     @JsonProperty("typeCreative") String typeCreative,
                     @JsonProperty("image") Image image,
                     @JsonProperty("audio") List<Audio> audio,
                     @JsonProperty("originalTitle") Map<Language, String> originalTitles,
                     @JsonProperty("publicationEvent") List<PublicationEvent> publicationEvents,
                     @JsonProperty("collection") String collection,
                     @JsonProperty("subject") List<Subject> subject,
                     @JsonProperty("subtitling") List<Subtitling> subtitling) {
        super(identity);
        this.season = season;
        this.series = series;
        this.alternativeIds = alternativeIds;
        this.descriptions = descriptions;
        this.creators = creators;
        this.video = video;
        this.typeMedia = typeMedia;
        this.episodeNumber = episodeNumber;
        this.promotionTitles = promotionTitles;
        this.indexDataModified = indexDataModified;
        this.duration = duration;
        this.productionId = productionId;
        this.contentRating = contentRating;
        this.titles = title;
        //this.itemTitles = itemTitles;
        this.countryOfOrigin = countryOfOrigin;
        this.typeCreative = typeCreative;
        this.image = image;
        this.audio = audio;
        this.originalTitles = originalTitles;
        this.publicationEvents = publicationEvents;
        this.collection = collection;
        this.subject = subject;
        this.subtitling = subtitling;
    }

    /**
     * @since 2015-08-04
     */
    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Season extends Entity<YleId> {
        private final Map<Language, String> description;
        private final int number;
        private final List<Creator> creators;
        private final YleId series;
        private final DateTime indexDataModified;
        private final Map<Language, String> title;
        private final List<String> countryOfOrigin;
        private final List<Subject> subjects;

        @JsonCreator
        public Season(@JsonProperty("description") Map<Language, String> description,
                      @JsonProperty("seasonNumber") int number,
                      @JsonProperty("creator") List<Creator> creators,
                      @JsonProperty("partOfSeries") Map<String, YleId> series,
                      @JsonProperty("indexDataModified") DateTime indexDataModified,
                      @JsonProperty("title") Map<Language, String> title,
                      @JsonProperty("countryOfOrigin") List<String> countryOfOrigin,
                      @JsonProperty("id") YleId id,
                      @JsonProperty("subject") List<Subject> subjects) {
            super(id, YleId.UNIDENTIFIED);
            this.description = description;
            this.number = number;
            this.creators = creators;
            this.series = series.get("id"); // TODO: hack to get around this serialization issue
            this.indexDataModified = indexDataModified;
            this.title = title;
            this.countryOfOrigin = countryOfOrigin;
            this.subjects = subjects;
        }
    }

    /**
     * @since 2015-08-04
     */
    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Series extends Entity<YleId> {

        private final Map<Language, String> descriptions;
        private final List<Creator> creators;
        private final DateTime indexDataModified;
        private final Map<Language, String> titles;
        private final Image coverImage;
        private final List<Season> seasons;
        private final List<String> countryOfOrigin;
        private final List<Subject> subjects;

        @JsonCreator
        public Series(
                @JsonProperty("id") YleId id,
                @JsonProperty("description") Map<Language, String> descriptions,
                @JsonProperty("creator") List<Creator> creators,
                @JsonProperty("indexDataModified") DateTime indexDataModified,
                @JsonProperty("title") Map<Language, String> titles,
                @JsonProperty("coverImage") Image coverImage,
                @JsonProperty("season") List<Season> seasons,
                @JsonProperty("countryOfOrigin") List<String> countryOfOrigin,
                @JsonProperty("subject") List<Subject> subjects) {
            super(id, YleId.UNIDENTIFIED);
            this.descriptions = descriptions;
            this.creators = creators;
            this.indexDataModified = indexDataModified;
            this.titles = titles;
            this.coverImage = coverImage;
            this.seasons = seasons;
            this.countryOfOrigin = countryOfOrigin;
            this.subjects = subjects;
        }
    }

    /**
     * @since 2015-08-04
     */
    @Data
    public static class Creator {
        private final String name;
        private final String type;

        @JsonCreator
        public Creator(@JsonProperty("name") String name,
                       @JsonProperty("type") String type) {
            this.name = name;
            this.type = type;
        }

    }

    /**
     * @since 2015-08-04
     */
    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Subject extends Entity<YleId> {

        private final Map<Language, String> titles;
        private final Optional<Set<YleId>> broader;
        private final String inScheme;
        private final String type;
        private final Optional<String> key;
        private final Optional<List<Notation>> notations;

        @JsonCreator
        public Subject(@JsonProperty("id") YleId id,
                       @JsonProperty("title") Map<Language, String> titles,
                       @JsonProperty("broader") Optional<Map<String, YleId>> broader,
                       @JsonProperty("inScheme") String inScheme,
                       @JsonProperty("type") String type,
                       @JsonProperty("key") Optional<String> key,
                       @JsonProperty("notation") Optional<List<Notation>> notations) {
            super(id, YleId.UNIDENTIFIED);
            this.titles = titles;
            this.broader = broader.isPresent() ? Optional.of(Sets.newHashSet(broader.get().values())) : Optional.absent(); // TODO: this is a bit of a hack around the "broader" concept as I don't fully know what its contents should be
            this.inScheme = inScheme;
            this.type = type;
            this.key = key;
            this.notations = notations;
        }

        @Data
        public static class Notation {
            private final String value;
            private final String valueType;

            @JsonCreator
            public Notation(@JsonProperty("value") String value,
                            @JsonProperty("valueType") String valueType) {
                this.value = value;
                this.valueType = valueType;
            }
        }
    }

    /**
     * @since 2015-08-04
     */
    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class PublicationEvent extends Entity<YleId> {
        private final Service service;
        private final Optional<List<Publisher>> publishers;
        private final DateTime startTime;
        private final String temporalStatus;
        private final DateTime endTime;
        private final String type;
        private final String duration;
        private final String region;
        private final YleId id;
        private final Optional<Media> media;

        @JsonCreator
        public PublicationEvent(@JsonProperty("service") Service service,
                                @JsonProperty("publisher") Optional<List<Publisher>> publishers,
                                @JsonProperty("startTime") DateTime startTime,
                                @JsonProperty("temporalStatus") String temporalStatus,
                                @JsonProperty("endTime") DateTime endTime,
                                @JsonProperty("type") String type,
                                @JsonProperty("duration") String duration,
                                @JsonProperty("region") String region,
                                @JsonProperty("id") YleId id,
                                @JsonProperty("media") Optional<Media> media) {
            super(id, YleId.UNIDENTIFIED);
            this.service = service;
            this.publishers = publishers;
            this.startTime = startTime;
            this.temporalStatus = temporalStatus;
            this.endTime = endTime;
            this.type = type;
            this.duration = duration;
            this.region = region;
            this.id = id;
            this.media = media;
        }

        @Data
        public static class Service {
            private final String id;

            @JsonCreator
            public Service(@JsonProperty("id") String id) {
                this.id = id;
            }

        }

        @Data
        public static class Publisher {
            private final String id;

            @JsonCreator
            public Publisher(@JsonProperty("id") String id) {
                this.id = id;
            }

            public String getId() {
                return id;
            }

        }

        @Data
        @EqualsAndHashCode(callSuper = true)
        public static class Media extends Entity<YleId> {
            private final String duration;
            private final List<ContentProtection> contentProtection;

            @JsonCreator
            public Media(@JsonProperty("id") YleId id,
                         @JsonProperty("duration") String duration,
                         @JsonProperty("contentProtection") List<ContentProtection> contentProtection) {
                super(id, YleId.UNIDENTIFIED);
                this.duration = duration;
                this.contentProtection = contentProtection;
            }

            @Data
            @EqualsAndHashCode(callSuper = true)
            public static class ContentProtection extends Entity<YleId> {
                private final String type;

                @JsonCreator
                public ContentProtection(@JsonProperty("id") YleId id,
                                         @JsonProperty("type") String type) {
                    super(id, YleId.UNIDENTIFIED);
                    this.type = type;
                }
            }
        }
    }

    /**
     * @since 2015-08-04
     */
    @Data
    public static class Subtitling {
        private final List<Language> languages;
        private final String type;

        @JsonCreator
        public Subtitling(@JsonProperty("language") List<Language> languages,
                          @JsonProperty("type") String type) {
            this.languages = languages;
            this.type = type;
        }
    }

    /**
     * @since 2015-08-04
     */
    @Data
    public static class ContentRating {
        private final Map<Language, String> titles;
        private final int ageRestriction;
        private final List<Reason> reasons;

        @JsonCreator
        public ContentRating(@JsonProperty("title") Map<Language, String> titles,
                             @JsonProperty("ageRestriction") int ageRestriction,
                             @JsonProperty("reason") List<Reason> reasons) {
            this.titles = titles;
            this.ageRestriction = ageRestriction;
            this.reasons = reasons;
        }

        @Data
        public static class Reason {
            private final Map<Language, String> titles;
            private final String type;
            private final String key;

            @JsonCreator
            public Reason(@JsonProperty("title") Map<Language, String> titles,
                          @JsonProperty("type") String type,
                          @JsonProperty("key") String key) {
                this.titles = titles;
                this.type = type;
                this.key = key;
            }
        }
    }

    /**
     * @since 2015-08-04
     */
    @Data
    public static class Audio {
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

    /**
     * @since 2015-08-04
     */
    @Data
    public static class Video {
        private final List<Format> formats;
        private final String type;

        @JsonCreator
        public Video(@JsonProperty("format") List<Format> formats,
                     @JsonProperty("type") String type) {
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

    /**
     * @since 2015-08-04
     */
    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Image extends Entity<YleId> {
        private final boolean available;
        private final String type;

        @JsonCreator
        public Image(@JsonProperty("id") YleId id,
                     @JsonProperty("available") boolean available,
                     @JsonProperty("type") String type) {
            super(id, YleId.UNIDENTIFIED);
            this.available = available;
            this.type = type;
        }

    }
}
