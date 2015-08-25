package io.induct.yle.api.tracking;

import io.induct.rest.Request;
import io.induct.yle.api.StandardizedApi;
import io.induct.yle.api.YleId;

/**
 * @since 2015-08-26
 */
public class YleTrackingApi extends StandardizedApi {
    public YleTrackingApi() {
        super("https://external.api.yle.fi");
    }

    public void reportStreamStart(YleId programId, YleId mediaId) {
        Request request = createRequestBuilder()
                .withPath("/v1/tracking/streamstart")
                .withParams(params -> {
                    params.put("program_id", programId.identity());
                    params.put("media_id", mediaId.identity());
                })
                .build();
        handleApiCall(request, null);
    }
}
