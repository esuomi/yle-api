package io.induct.yle.api.programs.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.induct.yle.api.YleId;
import io.induct.yle.api.common.Language;
import io.induct.yle.api.programs.domain.items.Service;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Map;

/**
 * @since 2015-09-11
 */
@Data
public class NowPlaying {
    private final DateTime startTime;
    private final DateTime endTime;
    private final String duration;
    private final Content content;
    private final Service service;
    private final String delta;
    private final PartOf partOf;

    @JsonCreator
    public NowPlaying(@JsonProperty("startTime") DateTime startTime,
                      @JsonProperty("endTime") DateTime endTime,
                      @JsonProperty("duration") String duration,
                      @JsonProperty("content") Content content,
                      @JsonProperty("service") Service service,
                      @JsonProperty("delta") String delta,
                      @JsonProperty("partOf") PartOf partOf) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.content = content;
        this.service = service;
        this.delta = delta;
        this.partOf = partOf;
    }

    @Data
    public static class Content {
        private final YleId id;
        private final String type;
        private final Map<Language, String> titles;
        private final List<Performer> performers;
        private final Map<Language, String> descriptions;

        @Data
        public static class Performer {
            private final String type;
            private final String name;

            @JsonCreator
            public Performer(@JsonProperty("type") String type,
                             @JsonProperty("name") String name) {
                this.type = type;
                this.name = name;
            }
        }
    }

    @Data
    public static class PartOf {
        private final YleId id;
        private final String type;
        private final Map<Language, String> titles;

        @JsonCreator
        public PartOf(@JsonProperty("id") YleId id,
                      @JsonProperty("type") String type,
                      @JsonProperty("title") Map<Language, String> titles) {
            this.id = id;
            this.type = type;
            this.titles = titles;
        }
    }

}
