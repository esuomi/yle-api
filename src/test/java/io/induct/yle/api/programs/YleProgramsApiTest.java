package io.induct.yle.api.programs;

import io.induct.http.testing.StaticResponse;
import io.induct.rest.ApiResponse;
import io.induct.yle.YleApiTestingBase;
import io.induct.yle.api.YleId;
import io.induct.yle.api.common.Language;
import io.induct.yle.api.common.MediaObject;
import io.induct.yle.api.programs.domain.CuratedList;
import io.induct.yle.api.programs.domain.Item;
import io.induct.yle.api.programs.domain.NowPlaying;
import io.induct.yle.api.programs.domain.items.Service;
import io.induct.yle.api.programs.domain.search.ItemSearch;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

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
    public void shouldGetSingleItem() throws Exception {

        whenCallingGet("https://external.api.yle.fi/v1/programs/items/1-2048985.json")
                .thenReturn(new StaticResponse(200, mapOf(), resource("api/v1/programs/items/1-2048985.json")));
        ApiResponse<Item> itemResponse = programsApi.getItem(new YleId("1-2048985"));

        verify(httpClient).get(
                eq("https://external.api.yle.fi/v1/programs/items/1-2048985.json"),
                eq(params()),
                eq(mapOf()),
                any(InputStream.class));
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
    }

    @Test
    public void shouldListServices() throws Exception {
        whenCallingGet("https://external.api.yle.fi/v1/programs/services.json")
                .thenReturn(new StaticResponse(200, mapOf(), resource("api/v1/programs/tvchannel_services.json")));

        ApiResponse<List<Service>> services = programsApi.listServices(Service.Type.TV_CHANNEL, 10, 0);

        verify(httpClient).get(
                eq("https://external.api.yle.fi/v1/programs/services.json"),
                eq(params("limit", "10", "offset", "0", "type", "tvchannel")),
                eq(mapOf()),
                any(InputStream.class));
    }

    /**
     * @see <a href="http://developer.yle.fi/tutorial-get-currently-playing-radio-program/">Yle Api tutorial: Finding out what’s playing on the radio</a>
     */
    @Test
    public void shouldBeAbleToGetUrlForCurrentlyPlayingRadioProgram() throws Exception {
        whenCallingGet("https://external.api.yle.fi/v1/programs/services.json")
                .thenReturn(new StaticResponse(200, mapOf(), resource("api/v1/programs/radiochannel_services.json")));

        ApiResponse<List<Service>> services = programsApi.listServices(Service.Type.RADIO_CHANNEL, 5, 0);

        verify(httpClient).get(
                eq("https://external.api.yle.fi/v1/programs/services.json"),
                eq(params("limit", "5", "offset", "0", "type", "radiochannel")),
                eq(mapOf()),
                any(InputStream.class));

    }

    @Test
    public void shouldListCuratedLists() throws Exception {
        whenCallingGet("https://external.api.yle.fi/v1/programs/lists.json")
                .thenReturn(new StaticResponse(200, mapOf(), resource("api/v1/programs/lists.json")));

        ApiResponse<List<CuratedList>> curatedLists = programsApi.listCuratedLists(Language.FINNISH, CuratedList.Type.RADIO_CONTENT, 10, 0);

        verify(httpClient).get(
                eq("https://external.api.yle.fi/v1/programs/lists.json"),
                eq(params("limit", "10", "offset", "0", "language", "fi", "type", "radiocontent")),
                eq(mapOf()),
                any(InputStream.class));
    }


    @Test
    public void shouldReturnNowPlaying() throws Exception {
        whenCallingGet("https://external.api.yle.fi/v1/programs/nowplaying/yle-radio-1.json")
                .thenReturn(new StaticResponse(200, mapOf(), resource("api/v1/programs/nowplaying.json")));

        ApiResponse<List<NowPlaying>> nowPlaying = programsApi.nowPlaying("yle-radio-1");

        verify(httpClient).get(
                eq("https://external.api.yle.fi/v1/programs/nowplaying/yle-radio-1.json"),
                eq(params("start", "-1", "end", "1")),
                eq(mapOf()),
                any(InputStream.class));
    }
}