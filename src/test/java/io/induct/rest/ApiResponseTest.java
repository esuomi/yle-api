package io.induct.rest;

import io.induct.daniel.Daniel;
import io.induct.daniel.DanielModule;
import io.induct.yle.PropertiesConfigModule;
import io.induct.yle.YleApiTestingBase;
import io.induct.yle.ioc.YleApiModule;
import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @since 2015-05-30
 */
@RunWith(JukitoRunner.class)
@UseModules({
        PropertiesConfigModule.class,
        DanielModule.class,
        YleApiModule.class
})
public class ApiResponseTest extends YleApiTestingBase {

    @Inject Daniel daniel;

    private Class<ApiResponse> responseType = ApiResponse.class;

    private static String API_RESPONSE_ROOT_EXAMPLE =
            "{\n"+
            "  \"apiVersion\": \"1.0.1288\",\n"+
            "  \"meta\": {\n"+
            "    \"offset\": \"0\",\n"+
            "    \"limit\": \"25\",\n"+
            "    \"count\": 2137976,\n"+
            "    \"program\": 2012109,\n"+
            "    \"clip\": 125857\n"+
            "  },\n"+
            "  \"data\":{}" +
            "}";

    @Test
    public void shouldDeserializeFromEmptyApiResponse() throws Exception {
        ApiResponse response = daniel.deserialize(responseType, new ByteArrayInputStream(API_RESPONSE_ROOT_EXAMPLE.getBytes()));
        assertEquals("1.0.1288", response.getApiVersion());
        Map<String, Object> meta = response.getMeta();
        assertEquals("0", meta.get("offset"));
        assertEquals("25", meta.get("limit"));
        assertEquals(2137976, meta.get("count"));
        assertEquals(2012109, meta.get("program"));
        assertEquals(125857, meta.get("clip"));
        assertNotNull(response.getData());
    }
}