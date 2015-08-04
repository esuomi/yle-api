package io.induct.yle.api.programs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.util.concurrent.RateLimiter;
import io.induct.daniel.Daniel;
import io.induct.http.Response;
import io.induct.rest.ApiResponse;
import io.induct.rest.Request;
import io.induct.rest.RequestBuilder;
import io.induct.yle.api.common.Infrastructure;
import io.induct.yle.api.common.Language;
import io.induct.yle.api.programs.model.CuratedList;
import io.induct.yle.api.programs.model.Item;
import io.induct.yle.api.programs.model.items.Service;
import io.induct.yle.api.programs.model.search.ItemSearch;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @since 2015-05-30
 */
public class YleProgramsApi {

    public static final String PROGRAMS_BASE_URL = "https://external.api.yle.fi";

    private final TypeReference<ApiResponse<List<Service>>> listOfServices;
    private final TypeReference<ApiResponse<List<Item>>> listOfItems;
    private final TypeReference<ApiResponse<List<CuratedList>>> listOfCuratedLists;

    private final RateLimiter rateLimiter;
    private final Infrastructure infrastructure;
    private final Daniel daniel;

    @Inject
    public YleProgramsApi(@Named("yle.api.rateLimit") RateLimiter rateLimiter,
                          Infrastructure infrastructure,
                          Daniel daniel) {
        this.listOfServices = new TypeReference<ApiResponse<List<Service>>>() {};
        this.listOfItems = new TypeReference<ApiResponse<List<Item>>>() {};
        this.listOfCuratedLists = new TypeReference<ApiResponse<List<CuratedList>>>() {};
        this.rateLimiter = rateLimiter;
        this.infrastructure = infrastructure;
        this.daniel = daniel;
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

        Response response = request.get();
        return daniel.deserialize(listOfServices, response.getResponseBody().get());
    }

    public ApiResponse<List<Item>> search(ItemSearch search) {
        Request request = createRequestBuilder()
                .withPath("/v1/programs/items.json")
                .withParams(params -> {
                    params.putAll(search.getParams());
                })
                .build();

        Response response = request.get();
        ApiResponse<List<Item>> stuff = daniel.deserialize(listOfItems, response.getResponseBody().get());
        return stuff;
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

        Response response = request.get();
        return daniel.deserialize(listOfCuratedLists, response.getResponseBody().get());
    }

    private RequestBuilder createRequestBuilder() {
        return infrastructure.createRequestBuilder(PROGRAMS_BASE_URL, rateLimiter);
    }
}
