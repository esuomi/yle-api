package io.induct.yle.api.programs;

import io.induct.rest.ApiResponse;
import io.induct.yle.YleApiTestingBase;
import io.induct.yle.api.programs.model.Item;
import io.induct.yle.api.programs.model.items.Service;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @since 2015-05-09
 */
public class YleProgramsApiTest extends YleApiTestingBase {

    private static final String TEST_PROGRAMS_ID = "testFeature";

    private YleProgramsApi programsApi;

    @Before
    public void setUp() throws Exception {
        programsApi = injector.getInstance(YleProgramsApi.class);
    }

    @Test
    public void shouldListProgramItems() throws Exception {
        ApiResponse<List<Item>> itemsResponse = programsApi.listItems();
        for (Item item : itemsResponse.getData()) {
            System.out.println("item = " + item.getClass());
        }
    }

    @Test
    public void shouldListServices() throws Exception {
        int limit = 10;
        int offset = 0;
        ApiResponse<List<Service>> services = programsApi.listServices(Service.Type.TV_CHANNEL, limit, offset);
    }

}