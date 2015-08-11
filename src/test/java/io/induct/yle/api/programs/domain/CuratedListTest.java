package io.induct.yle.api.programs.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.ImmutableMap;
import io.induct.daniel.Daniel;
import io.induct.rest.ApiResponse;
import io.induct.yle.YleApiTestingBase;
import io.induct.yle.api.YleId;
import io.induct.yle.api.common.Language;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @since 2015-07-17
 */
public class CuratedListTest extends YleApiTestingBase {

    private Daniel daniel;

    @Before
    public void setUp() throws Exception {
        daniel = injector.getInstance(Daniel.class);
    }

    private final TypeReference<ApiResponse<List<CuratedList>>> listOfCuratedLists = new TypeReference<ApiResponse<List<CuratedList>>>() {};

    @Test
    public void shouldDeserializeFromExampleApiResponse() throws Exception {
        ApiResponse<List<CuratedList>> curatedLists = daniel.deserialize(
                listOfCuratedLists,
                resource("api/v1/programs/lists.json").get());

        assertThat(curatedLists.getData().size(), equalTo(38));
        CuratedList curatedList = curatedLists.getData().get(0);
        assertThat(curatedList.identity(), equalTo(new YleId("8-1802228")));
        assertThat(curatedList.getAmountOfItems(), equalTo(18));
        assertThat(curatedList.getLanguages(), equalTo(Arrays.asList(Language.FINNISH)));
        // TODO: list parameter might be unused?
        // assertThat(curatedList.getList(), equalTo(Collections.emptyList()));
        assertThat(curatedList.getTitles(), equalTo(ImmutableMap.of(Language.FINNISH, "Suosittelemme")));
    }
}