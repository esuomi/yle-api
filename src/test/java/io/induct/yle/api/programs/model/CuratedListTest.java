package io.induct.yle.api.programs.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Resources;
import io.induct.daniel.Daniel;
import io.induct.daniel.DanielModule;
import io.induct.rest.ApiResponse;
import io.induct.yle.PropertiesConfigModule;
import io.induct.yle.api.YleId;
import io.induct.yle.api.common.Language;
import io.induct.yle.ioc.YleApiModule;
import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @since 2015-07-17
 */
@RunWith(JukitoRunner.class)
@UseModules({
        PropertiesConfigModule.class,
        DanielModule.class,
        YleApiModule.class
})
public class CuratedListTest {

    @Inject
    Daniel daniel;

    private final TypeReference<ApiResponse<List<CuratedList>>> listOfCuratedLists = new TypeReference<ApiResponse<List<CuratedList>>>() {};

    @Test
    public void shouldDeserializeFromExampleApiResponseFromDeocumentation() throws Exception {
        ApiResponse<List<CuratedList>> curatedLists = daniel.deserialize(
                listOfCuratedLists,
                Resources.getResource("CuratedListResponse.json").openStream());

        assertThat(curatedLists.getData().size(), equalTo(38));
        CuratedList curatedList = curatedLists.getData().get(0);
        assertThat(curatedList.identity(), equalTo(new YleId("8-1802228")));
        assertThat(curatedList.getAmountOfItems(), equalTo(18));
        assertThat(curatedList.getLanguages(), equalTo(Arrays.asList(Language.FINNISH)));
        // TODO: list parameter might be unused?
        // assertThat(curatedList.getList(), equalTo(Collections.emptyList()));
        assertThat(curatedList.getTitles(), equalTo(ImmutableMap.<Language, String>of(Language.FINNISH, "Suosittelemme")));
    }
}