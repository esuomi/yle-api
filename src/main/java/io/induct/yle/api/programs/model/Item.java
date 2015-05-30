package io.induct.yle.api.programs.model;

import io.induct.domain.Entity;
import io.induct.yle.api.YleId;

/**
 * @since 2015-05-30
 */
public class Item extends Entity<YleId> {
    protected Item(YleId identity) {
        super(identity, YleId.UNIDENTIFIED);
    }
}
