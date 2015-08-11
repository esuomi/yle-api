package io.induct.yle.api.programs.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.induct.domain.Entity;
import io.induct.yle.api.YleId;
import io.induct.yle.api.programs.domain.items.GenericItem;
import io.induct.yle.api.programs.domain.items.RadioProgram;
import io.induct.yle.api.programs.domain.items.TvClip;
import io.induct.yle.api.programs.domain.items.TvProgram;
import lombok.EqualsAndHashCode;

/**
 * @since 2015-05-30
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type",
        visible = true,
        defaultImpl = GenericItem.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = RadioProgram.class, name = "RadioProgram"),
        @JsonSubTypes.Type(value = TvProgram.class, name = "TVProgram"),
        @JsonSubTypes.Type(value = TvClip.class, name = "TVClip")
})
@EqualsAndHashCode(callSuper = true)
public abstract class Item extends Entity<YleId> {

    public Item(YleId identity) {
        super(identity, YleId.UNIDENTIFIED);
    }
}
