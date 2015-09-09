package io.induct.yle.api;

import io.induct.yle.api.media.YleMediaApi;
import io.induct.yle.api.programs.YleProgramsApi;
import io.induct.yle.api.tracking.YleTrackingApi;
import io.induct.yle.cdn.images.YleImagesCdn;

import javax.inject.Inject;

/**
 * @since 2015-05-09
 */
public class YleApi {

    private final YleProgramsApi programsApi;
    private final YleMediaApi mediaApi;
    private final YleTrackingApi trackingApi;
    private final YleImagesCdn imageCdn;

    @Inject
    public YleApi(YleProgramsApi programsApi, YleMediaApi mediaApi, YleTrackingApi trackingApi, YleImagesCdn imageCdn) {
        this.programsApi = programsApi;
        this.mediaApi = mediaApi;
        this.trackingApi = trackingApi;
        this.imageCdn = imageCdn;
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

    public YleImagesCdn imagesCdn() {
        return imageCdn;
    }
}
