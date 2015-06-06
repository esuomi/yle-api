package io.induct.yle.api.programs.model.items;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.induct.yle.api.YleId;
import io.induct.yle.api.programs.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @since 2015-05-30
 */
public class GenericItem extends Item {

    private final Logger log = LoggerFactory.getLogger(GenericItem.class);

    @JsonCreator
    public GenericItem(@JsonProperty("id") YleId identity,
                       @JsonProperty("type") String type) {
        super(identity, type);
        log.warn("Unknown type '{}' mapped to GenericItem; library needs to be updated for support", type);
    }
}
