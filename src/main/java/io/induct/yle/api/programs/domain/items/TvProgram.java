package io.induct.yle.api.programs.domain.items;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.induct.yle.api.YleId;
import io.induct.yle.api.programs.domain.Item;

/**
 * @since 2015-05-30
 */
public class TvProgram extends Item {

    @JsonCreator
    public TvProgram(@JsonProperty("id") YleId identity,
                     @JsonProperty("type") String type) {
        super(identity, type);
    }
}
