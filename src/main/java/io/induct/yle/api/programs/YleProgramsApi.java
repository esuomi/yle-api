package io.induct.yle.api.programs;

import com.fasterxml.jackson.core.type.TypeReference;
import io.induct.rest.ApiResponse;
import io.induct.rest.Request;
import io.induct.yle.api.StandardizedApi;
import io.induct.yle.api.YleId;
import io.induct.yle.api.common.Language;
import io.induct.yle.api.programs.domain.CuratedList;
import io.induct.yle.api.programs.domain.Item;
import io.induct.yle.api.programs.domain.NowPlaying;
import io.induct.yle.api.programs.domain.items.Service;
import io.induct.yle.api.programs.domain.search.ItemSearch;

import javax.inject.Inject;
import java.util.List;

/**
 * @since 2015-05-30
 */
public class YleProgramsApi extends StandardizedApi {

    public static final String PROGRAMS_BASE_URL = "https://external.api.yle.fi";

    private final TypeReference<ApiResponse<List<Service>>> listOfServices;
    private final TypeReference<ApiResponse<List<Item>>> listOfItems;
    private final TypeReference<ApiResponse<Item>> singleItem;
    private final TypeReference<ApiResponse<List<CuratedList>>> listOfCuratedLists;

    @Inject
    public YleProgramsApi() {
        super(PROGRAMS_BASE_URL);
        this.listOfServices = new TypeReference<ApiResponse<List<Service>>>() {};
        this.listOfItems = new TypeReference<ApiResponse<List<Item>>>() {};
        this.singleItem = new TypeReference<ApiResponse<Item>>() {};
        this.listOfCuratedLists = new TypeReference<ApiResponse<List<CuratedList>>>() {};
    }

    public ApiResponse<List<Service>> listServices(Service.Type type, int limit, int offset) {
        Request request = createRequestBuilder()
                .withPath("/v1/programs/services.json")
                .withParams(params -> {
                    params.put("type", type.value());
                    params.put("limit", Integer.toString(limit));
                    params.put("offset", Integer.toString(offset));
                })
                .build();

        return handleApiCall(request, listOfServices);
    }

    public ApiResponse<List<Item>> search(ItemSearch search) {
        Request request = createRequestBuilder()
                .withPath("/v1/programs/items.json")
                .withParams(params -> {
                    params.putAll(search.getParams());
                })
                .build();

        return handleApiCall(request, listOfItems);
    }

    public ApiResponse<List<CuratedList>> listCuratedLists(Language language, CuratedList.Type type, int limit, int offset) {
        Request request = createRequestBuilder()
                .withPath("/v1/programs/lists.json")
                .withParams(params -> {
                    params.put("language", language.getLanguageCode());
                    params.put("type", type.value());
                    params.put("limit", Integer.toString(limit));
                    params.put("offset", Integer.toString(offset));
                })
                .build();

        return handleApiCall(request, listOfCuratedLists);
    }

    public ApiResponse<Item> getItem(YleId yleId) {
        Request request = createRequestBuilder()
                .withPath("/v1/programs/items/" + yleId.identity() + ".json")
                .build();

        return handleApiCall(request, singleItem);
    }

    public ApiResponse<NowPlaying> nowPlaying(String id) {
        Request request = createRequestBuilder()
                .withPath("/v1/programs/nowplaying/" + id + ".json")
                .withParams(params -> {
                    params.put("start", Integer.toString(-1));
                    params.put("end", Integer.toString(1));
                })
                .build();

        return handleApiCall(request, null);
    }
}
