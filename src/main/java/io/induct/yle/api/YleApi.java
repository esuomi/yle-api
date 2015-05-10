package io.induct.yle.api;

import io.induct.yle.api.profiles.YleProfilesApi;

import javax.inject.Inject;

/**
 * @since 2015-05-09
 */
public class YleApi {

    private final YleProfilesApi profilesApi;

    @Inject
    public YleApi(YleProfilesApi profilesApi) {
        this.profilesApi = profilesApi;
    }

    public YleProfilesApi profiles() {
        return profilesApi;
    }
}
