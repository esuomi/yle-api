package io.induct.yle.api.programs.model.search;

import io.induct.yle.api.YleId;

import java.util.HashSet;
import java.util.Set;

/**
 * @since 2015-05-30
 */
public class ItemSearch {

    public enum Type {
        PROGRAM("program"),
        CLIP("clip"),
        TV_CONTENT("tvcontent"),
        TV_PROGRAM("tvprogram"),
        TV_CLIP("tvclip"),
        RADIO_CONTENT("radiocontent"),
        RADIO_PROGRAM("radioprogram"),
        RADIO_CLIP("radioclip");

        private final String apiParamName;

        Type(String apiParamName) {
            this.apiParamName = apiParamName;
        }

        public String getApiParamName() {
            return apiParamName;
        }
    }

    private ItemSearch(Set<YleId> ids, Type type) {

    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        // TODO: All the params http://docs.developer.yle.fi/#/programs-api-search-programs-clips-and-episodes
        private Set<YleId> ids;
        private Type type;
        private String keyword;

        private Builder() {
            this.ids = new HashSet<>();
        }

        public Builder byIds(YleId first, YleId... more) {
            ids.add(first);
            for (YleId yleId : more) {
                ids.add(yleId);
            }
            return this;
        }

        public Builder byIds(String first, String... more) {
            ids.add(new YleId(first));
            for (String id : more) {
                ids.add(new YleId(id));
            }
            return this;
        }

        public Builder ofType(Type type) {
            this.type = type;
            return this;
        }

        public ItemSearch build() {
            return new ItemSearch(ids, type);
        }

        public Builder withKeyword(String keyword) {
            this.keyword = keyword;
            return this;
        }
    }
}
