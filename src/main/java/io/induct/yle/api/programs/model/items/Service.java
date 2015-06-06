package io.induct.yle.api.programs.model.items;

/**
 * @since 2015-05-30
 */
public class Service {
    public enum Type {
        TV_CHANNEL("tvchannel"),
        RADIO_CHANNEL("radiochannel"),
        ON_DEMAND("ondemandservice"),
        WEBCAST("webcastservice");

        private final String value;

        Type(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
