package io.induct.yle.api.programs;

import io.induct.rest.ApiResponse;
import io.induct.yle.YleApiTestingBase;
import io.induct.yle.api.common.Language;
import io.induct.yle.api.common.MediaObject;
import io.induct.yle.api.programs.model.Item;
import io.induct.yle.api.programs.model.items.Service;
import io.induct.yle.api.programs.model.search.ItemSearch;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
        ItemSearch itemSearch = ItemSearch.builder()
                .ofType(ItemSearch.Type.TV_CONTENT)
                .withKeyword("muumi")
                .returnOnly(MediaObject.VIDEO)
                //.inCategory() TODO: category support can be added only once the get categories API is supported
                .isDownloadable(false)
                .inLanguage(Language.FINNISH)
                .build();
        ApiResponse<List<Item>> itemsResponse = programsApi.search(itemSearch);
        assertThat(itemsResponse.getData().isEmpty(), is(false));
    }

    @Test
    public void shouldListServices() throws Exception {
        int limit = 10;
        int offset = 0;
        ApiResponse<List<Service>> services = programsApi.listServices(Service.Type.TV_CHANNEL, limit, offset);
    }

}