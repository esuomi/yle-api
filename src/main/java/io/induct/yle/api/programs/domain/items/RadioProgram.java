package io.induct.yle.api.programs.domain.items;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.induct.yle.api.YleId;
import io.induct.yle.api.programs.domain.Item;

/**
 * @since 2015-05-30
 */
public class RadioProgram extends Item {

    @JsonCreator
    public RadioProgram(@JsonProperty("id") YleId identity) {
        super(identity);
    }
}
