package io.induct.yle.api.media.domain;

import io.induct.yle.api.YleId;

/**
 * @since 2015-08-25
 */
public class DecryptedPlayout extends Playout {
    public DecryptedPlayout(int height,
                            int width,
                            Protocol protocol,
                            boolean multibitrate,
                            boolean live,
                            YleId formatOf,
                            String type,
                            String protectionType,
                            String url) {
        super(height, width, protocol, multibitrate, live, formatOf, type, protectionType, url);
    }
}
