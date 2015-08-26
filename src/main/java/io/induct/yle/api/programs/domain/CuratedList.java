package io.induct.yle.api.programs.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableMap;
import io.induct.domain.Entity;
import io.induct.yle.api.YleId;
import io.induct.yle.api.common.Language;

import java.util.List;
import java.util.Map;

/**
 * @since 2015-07-17
 */
public class CuratedList extends Entity<YleId> {

    private final int amountOfItems;
    private final List<Language> languages;
    private final Map<Language, String> titles;

    @JsonCreator
    public CuratedList(@JsonProperty("id") YleId identity,
                       @JsonProperty("amountOfItems") int amountOfItems,
                       @JsonProperty("language") List<Language> languages,
                       @JsonProperty("title") ImmutableMap<Language, String> titles) {
        super(identity, YleId.UNIDENTIFIED);
        this.amountOfItems = amountOfItems;
        this.languages = languages;
        this.titles = titles;
    }

    public int getAmountOfItems() {
        return amountOfItems;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public Map<Language, String> getTitles() {
        return titles;
    }


    public enum Type {
        RADIO_CONTENT("radiocontent"),
        TV_CONTENT("tvcontent");

        private final String value;

        Type(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
