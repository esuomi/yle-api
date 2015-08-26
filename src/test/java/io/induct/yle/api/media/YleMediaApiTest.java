package io.induct.yle.api.media;

import io.induct.http.testing.StaticResponse;
import io.induct.rest.ApiResponse;
import io.induct.yle.YleApiTestingBase;
import io.induct.yle.api.YleId;
import io.induct.yle.api.media.domain.EncryptedPlayout;
import io.induct.yle.api.media.domain.Playout;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * @since 2015-08-25
 */
public class YleMediaApiTest extends YleApiTestingBase {

    private YleMediaApi mediaApi;

    @Before
    public void setUp() throws Exception {
        mediaApi = injector.getInstance(YleMediaApi.class);
    }

    @Test
    public void shouldBeAbleToAccessPlayouts() throws Exception {
        YleId mediaId = new YleId("6-35af958db127424fbf43694a14ece041");
        YleId programId = new YleId("1-234");
        whenCallingGet("https://external.api.yle.fi/v1/media/playouts.json")
                .thenReturn(new StaticResponse(200, mapOf(), resource("api/v1/media/playouts/" + mediaId.identity() + ".json")));

        ApiResponse<List<EncryptedPlayout>> playouts = mediaApi.getPlayouts(programId, mediaId, Playout.Protocol.HLS);

        verify(httpClient).get(
                eq("https://external.api.yle.fi/v1/media/playouts.json"),
                eq(params("media_id", mediaId.identity(), "program_id", programId.identity(), "protocol", "HLS")),
                eq(mapOf()),
                any(InputStream.class));
    }
}