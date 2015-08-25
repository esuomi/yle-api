package io.induct.yle.api.media.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.induct.yle.api.YleId;

/**
 * @since 2015-08-25
 */
public class EncryptedPlayout extends Playout {

    @JsonCreator
    public EncryptedPlayout(@JsonProperty("height") int height,
                            @JsonProperty("width") int width,
                            @JsonProperty("protocol") Protocol protocol,
                            @JsonProperty("multibitrate") boolean multibitrate,
                            @JsonProperty("live") boolean live,
                            @JsonProperty("formatOf") YleId formatOf,
                            @JsonProperty("type") String type,
                            @JsonProperty("protectionType") String protectionType,
                            @JsonProperty("url") String url) {
        super(height, width, protocol, multibitrate, live, formatOf, type, protectionType, url);
    }

}
