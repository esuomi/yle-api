package io.induct.yle.api.programs.domain.search;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;
import io.induct.domain.Identifiable;
import io.induct.yle.api.YleId;
import io.induct.yle.api.common.Language;
import io.induct.yle.api.common.MediaObject;

import java.util.HashSet;
import java.util.Set;

/**
 * @since 2015-05-30
 */
public class ItemSearch {

    private static final Joiner csv = Joiner.on(',').skipNulls();

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

    public enum Availability {
        ON_DEMAND("ondemand"),
        FUTURE_ON_DEMAND("future-ondemand"),
        FUTURE_SCHEDULED("future-scheduled"),
        IN_FUTURE("in-future");

        private final String apiParamName;

        Availability(String apiParamName) {
            this.apiParamName = apiParamName;
        }

        public String getApiParamName() {
            return apiParamName;
        }
    }

    private final Set<YleId> ids;

    private final Type type;

    private final String keyword;

    private final MediaObject mediaObject;

    private final Set<YleId> seriesIds;
    private final Availability availability;
    private final boolean downloadable;
    private final Language language;

    private ItemSearch(Set<YleId> ids, Type type, String keyword, MediaObject mediaObject, Set<YleId> seriesIds, Availability availability, boolean downloadable, Language language) {
        this.ids = ids;
        this.type = type;
        this.keyword = keyword;
        this.mediaObject = mediaObject;
        this.seriesIds = seriesIds;
        this.availability = availability;
        this.downloadable = downloadable;
        this.language = language;
    }

    public Multimap<String, String> getParams() {
        Multimap<String, String> params = HashMultimap.create();

        if (!ids.isEmpty())
            params.put("id", csv.join(Iterables.transform(ids, Identifiable::identity)));
        if (type != null)
            params.put("type", type.getApiParamName());
        if (!Strings.isNullOrEmpty(keyword))
            params.put("q", keyword);
        if (mediaObject != null)
            params.put("mediaobject", mediaObject.getApiParamName());
        if (!seriesIds.isEmpty())
            params.put("series", csv.join(Iterables.transform(seriesIds, Identifiable::identity)));
        if (availability != null)
            params.put("availability", availability.getApiParamName());
        if (downloadable)
            params.put("downloadable", "true");
        if (language != null)
            params.put("language", language.getLanguageCode());

        return params;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        // TODO: All the params http://docs.developer.yle.fi/#/programs-api-search-programs-clips-and-episodes
        private Set<YleId> ids;
        private Set<YleId> seriesIds;
        private Type type = null;
        private String keyword = null;
        private MediaObject mediaObject;
        private Availability availability;
        private boolean downloadable;
        private Language language;

        private Builder() {
            this.ids = new HashSet<>();
            this.seriesIds = new HashSet<>();
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
            return new ItemSearch(ids, type, keyword, mediaObject, seriesIds, availability, downloadable, language);
        }

        public Builder withKeyword(String keyword) {
            this.keyword = keyword;
            return this;
        }

        public Builder returnOnly(MediaObject mediaObject) {
            this.mediaObject = mediaObject;
            return this;
        }

        public Builder partOfSeries(YleId first, YleId... more) {
            seriesIds.add(first);
            for (YleId yleId : more) {
                seriesIds.add(yleId);
            }
            return this;
        }

        public Builder partOfSeries(String first, String... more) {
            seriesIds.add(new YleId(first));
            for (String id : more) {
                seriesIds.add(new YleId(id));
            }
            return this;
        }

        public Builder withAvailability(Availability availability) {
            this.availability = availability;
            return this;
        }

        public Builder isDownloadable(boolean downloadable) {
            this.downloadable = downloadable;
            return this;
        }

        public Builder inLanguage(Language language) {
            if (language != Language.FINNISH && language != Language.SWEDISH) {
                throw new IllegalArgumentException("Only Finnish and Swedish are supported as item search by language parameter. Was given " + language + " instead");
            }
            this.language = language;
            return this;
        }
    }
}
