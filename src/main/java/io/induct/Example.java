package io.induct;

import io.induct.yle.api.YleApi;
import io.induct.yle.api.YleId;
import io.induct.yle.api.profiles.model.Feature;
import io.induct.yle.api.profiles.YleProfilesApi;

/**
 * @since 2015-05-11
 */
public class Example {

    public static void main(String[] args) {
        YleApi yleApi = null;

        YleProfilesApi profilesApi = yleApi.profiles();

        YleId identity = new YleId("3-24234");

        Feature feature = new Feature(identity);
        profilesApi.save(feature);
    }
}
