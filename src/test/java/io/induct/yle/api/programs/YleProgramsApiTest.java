package io.induct.yle.api.programs;

import com.google.common.base.Preconditions;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import io.induct.http.Response;
import io.induct.http.testing.StaticResponse;
import io.induct.rest.ApiResponse;
import io.induct.yle.TestDependenciesModule;
import io.induct.yle.YleApiTestingBase;
import io.induct.yle.api.common.Language;
import io.induct.yle.api.common.MediaObject;
import io.induct.yle.api.programs.domain.CuratedList;
import io.induct.yle.api.programs.domain.Item;
import io.induct.yle.api.programs.domain.items.Service;
import io.induct.yle.api.programs.domain.search.ItemSearch;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.OngoingStubbing;

import java.io.InputStream;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @since 2015-05-09
 */
public class YleProgramsApiTest extends YleApiTestingBase {

    private YleProgramsApi programsApi;

    @Before
    public void setUp() throws Exception {
        programsApi = injector.getInstance(YleProgramsApi.class);
    }

    @Test
    public void shouldListProgramItems() throws Exception {
        whenCallingGet("https://external.api.yle.fi/v1/programs/items.json")
                .thenReturn(new StaticResponse(200, mapOf(), resource("api/v1/programs/items.json")));

        ItemSearch itemSearch = ItemSearch.builder()
                .ofType(ItemSearch.Type.TV_CONTENT)
                .withKeyword("muumi")
                .returnOnly(MediaObject.VIDEO)
                        //.inCategory() TODO: category support can be added only once the get categories API is supported
                .isDownloadable(false)
                .inLanguage(Language.FINNISH)
                .build();
        ApiResponse<List<Item>> itemsResponse = programsApi.search(itemSearch);

        verify(httpClient).get(
                eq("https://external.api.yle.fi/v1/programs/items.json"),
                eq(params("q", "muumi", "language", "fi", "type", "tvcontent", "mediaobject", "video")),
                eq(mapOf()),
                any(InputStream.class));


        assertThat(itemsResponse.getData().isEmpty(), is(false));
    }

    @Test
    public void shouldListServices() throws Exception {
        whenCallingGet("https://external.api.yle.fi/v1/programs/services.json")
                .thenReturn(new StaticResponse(200, mapOf(), resource("api/v1/programs/services.json")));

        programsApi.listServices(Service.Type.TV_CHANNEL, 10, 0);

        verify(httpClient).get(
                eq("https://external.api.yle.fi/v1/programs/services.json"),
                eq(params("limit", "10", "offset", "0", "type", "tvchannel")),
                eq(mapOf()),
                any(InputStream.class));
    }

    @Test
    public void shouldListCuratedLists() throws Exception {
        whenCallingGet("https://external.api.yle.fi/v1/programs/lists.json")
                .thenReturn(new StaticResponse(200, mapOf(), resource("api/v1/programs/lists.json")));

        programsApi.listCuratedLists(Language.FINNISH, CuratedList.Type.RADIO_CONTENT, 10, 0);

        verify(httpClient).get(
                eq("https://external.api.yle.fi/v1/programs/lists.json"),
                eq(params("limit", "10", "offset", "0", "language", "fi", "type", "radiocontent")),
                eq(mapOf()),
                any(InputStream.class));
    }

    private static Multimap<String, String> params(String... vals) {
        Multimap<String, String> map = mapOf(vals);
        map.put("app_id", TestDependenciesModule.TESTING_APP_ID);
        map.put("app_key", TestDependenciesModule.TESTING_APP_KEY);
        return map;
    }

    private OngoingStubbing<Response> whenCallingGet(String url) {
        return when(httpClient.get(eq(url), any(Multimap.class), any(Multimap.class), any(InputStream.class)));
    }

    private static Multimap<String, String> mapOf(String... vals) {
        Preconditions.checkArgument(vals.length % 2 == 0, "Must provide even amount of values, was given " + vals.length);
        ListMultimap<String, String> map = MultimapBuilder.hashKeys().arrayListValues().build();
        for (int i = 0; i < vals.length;) {
            map.put(vals[i++], vals[i++]);
        }
        return map;
    }
}