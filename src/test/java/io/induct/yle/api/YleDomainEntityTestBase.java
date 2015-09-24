package io.induct.yle.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Maps;
import io.induct.daniel.Daniel;
import io.induct.rest.ApiResponse;
import io.induct.yle.YleApiTestingBase;
import io.induct.yle.api.common.Language;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @since 2015-09-11
 */
public abstract class YleDomainEntityTestBase<D> extends YleApiTestingBase {

    private Daniel daniel;

    @Before
    public void domainTest_setUp() throws Exception {
        daniel = injector.getInstance(Daniel.class);
    }

    @Test
    public void shouldDeserializeFromExampleApiResponse() throws Exception {
        TypeReference<ApiResponse<D>> type = typeDefinition();
        ApiResponse<D> item = daniel.deserialize(type, resource(testResource()).get());
        D deserialized = item.getData();

        assertThat(deserialized, is(equalTo(expectedDomainEntity())));
    }

    protected abstract TypeReference<ApiResponse<D>> typeDefinition();

    protected abstract String testResource();

    protected abstract D expectedDomainEntity();

    protected static Map<Language, String> localized(Object... vals) {
        Map<Language, String> map = Maps.newLinkedHashMap();
        for (int i = 0; i < vals.length; ) {
            map.put((Language) vals[i++], (String) vals[i++]);
        }
        return map;
    }
}
