package io.induct.yle.api.media;

import com.fasterxml.jackson.core.type.TypeReference;
import io.induct.rest.ApiResponse;
import io.induct.rest.Request;
import io.induct.yle.api.StandardizedApi;
import io.induct.yle.api.YleId;
import io.induct.yle.api.media.domain.EncryptedPlayout;

import java.util.List;

/**
 * @since 2015-08-25
 */
public class YleMediaApi extends StandardizedApi {

    public static final String MEDIA_BASE_URL = "https://external.api.yle.fi";
    private TypeReference<ApiResponse<List<EncryptedPlayout>>> listOfPlayouts = new TypeReference<ApiResponse<List<EncryptedPlayout>>>() {};

    public YleMediaApi() {
        super(MEDIA_BASE_URL);
    }

    public ApiResponse<List<EncryptedPlayout>> getPlayouts(YleId programId, YleId mediaId, EncryptedPlayout.Protocol protocol) {
        Request request = createRequestBuilder()
                .withPath("/v1/media/playouts.json")
                .withParams(params -> {
                    params.put("program_id", programId.identity());
                    params.put("media_id", mediaId.identity());
                    params.put("protocol", protocol.toString());
                })
                .build();

        return handleApiCall(request, listOfPlayouts);
    }
}
