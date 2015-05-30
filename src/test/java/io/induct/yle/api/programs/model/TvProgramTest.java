package io.induct.yle.api.programs.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.induct.yle.api.YleId;

/**
 * @since 2015-05-30
 */
public class TvProgramTest extends Item {

    @JsonCreator
    public TvProgramTest(@JsonProperty("id") YleId identity,
                         @JsonProperty("type") String type) {
        super(identity, type);
    }
}