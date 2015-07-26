package io.induct.yle.api.programs;

import io.induct.daniel.DanielModule;
import io.induct.rest.ApiResponse;
import io.induct.yle.PropertiesConfigModule;
import io.induct.yle.YleApiTestingBase;
import io.induct.yle.api.common.Language;
import io.induct.yle.api.common.MediaObject;
import io.induct.yle.api.programs.domain.CuratedList;
import io.induct.yle.api.programs.domain.Item;
import io.induct.yle.api.programs.domain.items.Service;
import io.induct.yle.api.programs.domain.search.ItemSearch;
import io.induct.yle.ioc.YleApiModule;
import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @since 2015-05-09
 */
@RunWith(JukitoRunner.class)
@UseModules({
        PropertiesConfigModule.class,
        DanielModule.class,
        YleApiModule.class
})
public class YleProgramsApiTest extends YleApiTestingBase {

    @Inject YleProgramsApi programsApi;

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

    @Test
    public void shouldListCuratedLists() throws Exception {
        int limit = 10;
        int offset = 0;
        ApiResponse<List<CuratedList>> curatedLists = programsApi.listCuratedLists(Language.FINNISH, CuratedList.Type.RADIO_CONTENT, limit, offset);
        System.out.println("curatedLists = " + curatedLists);
    }
}