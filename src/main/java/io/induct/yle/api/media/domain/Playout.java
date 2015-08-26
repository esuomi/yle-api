package io.induct.yle.api.media.domain;

import io.induct.yle.api.YleId;
import lombok.Data;

/**
 * @since 2015-08-25
 */
@Data
public abstract class Playout {
    public enum Protocol {
        HLS,
        HDS,
        PMD,
        RTMPE;
    }

    private final int height;
    private final int width;
    private final Protocol protocol;
    private final boolean multibitrate;
    private final boolean live;
    private final YleId formatOf;
    private final String type;
    private final String protectionType; // TODO: This might be an enum
    private final String url;
    // TODO: Need example for "subtitles" property

    public Playout(int height,
                   int width,
                   Protocol protocol,
                   boolean multibitrate,
                   boolean live,
                   YleId formatOf,
                   String type,
                   String protectionType,
                   String url) {
        this.height = height;
        this.width = width;
        this.protocol = protocol;
        this.multibitrate = multibitrate;
        this.live = live;
        this.formatOf = formatOf;
        this.type = type;
        this.protectionType = protectionType;
        this.url = url;
    }
}