package io.induct.yle.api;

import io.induct.yle.api.media.YleMediaApi;
import io.induct.yle.api.programs.YleProgramsApi;
import io.induct.yle.api.tracking.YleTrackingApi;

import javax.inject.Inject;

/**
 * @since 2015-05-09
 */
public class YleApi {

    private final YleProgramsApi programsApi;
    private final YleMediaApi mediaApi;
    private final YleTrackingApi trackingApi;

    @Inject
    public YleApi(YleProgramsApi programsApi, YleMediaApi mediaApi, YleTrackingApi trackingApi) {
        this.programsApi = programsApi;
        this.mediaApi = mediaApi;
        this.trackingApi = trackingApi;
    }

    public YleProgramsApi programs() {
        return programsApi;
    }

    public YleMediaApi media() {
        return mediaApi;
    }

    public YleTrackingApi tracking() {
        return trackingApi;
    }
}
