package io.induct.yle.api.programs.model.items;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.induct.yle.api.YleId;
import io.induct.yle.api.programs.model.Item;

/**
 * @since 2015-05-30
 */
public class RadioProgram extends Item {

    @JsonCreator
    public RadioProgram(@JsonProperty("id") YleId identity,
                        @JsonProperty("type") String type) {
        super(identity, type);
    }
}