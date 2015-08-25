package io.induct.yle.api.tracking;

import io.induct.http.testing.StaticResponse;
import io.induct.yle.YleApiTestingBase;
import io.induct.yle.api.YleId;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * @since 2015-08-26
 */
public class YleTrackingApiTest extends YleApiTestingBase {

    private YleTrackingApi api;

    @Before
    public void setUp() throws Exception {
        api = injector.getInstance(YleTrackingApi.class);
    }

    @Test
    public void shouldBeAbleToReportTracking() throws Exception {
        YleId programId = new YleId(YleId.Type.PROGRAM_OR_SERIES.getPrefix() + "234");
        YleId mediaId = new YleId(YleId.Type.MEDIA.getPrefix() + "567");
        whenCallingGet("https://external.api.yle.fi/v1/tracking/streamstart")
                .thenReturn(new StaticResponse(200, mapOf(), noResponseBody()));

        api.reportStreamStart(programId, mediaId);

        verify(httpClient).get(
                eq("https://external.api.yle.fi/v1/tracking/streamstart"),
                eq(params("program_id", programId.identity(), "media_id", mediaId.identity())),
                eq(mapOf()),
                any(InputStream.class));
    }




}