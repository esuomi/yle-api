package io.induct.yle;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.google.common.io.Resources;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.induct.daniel.DanielModule;
import io.induct.http.HttpClient;
import io.induct.http.Response;
import io.induct.yle.ioc.YleApiModule;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import java.io.IOException;
import java.io.InputStream;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * @since 2015-05-09
 */
public class YleApiTestingBase {

    protected Injector injector;

    @Mock protected HttpClient httpClient;

    // NOTE: to guarantee @Before/@After are run first from this class they must have unique method names too

    @Before
    public void commonSetUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        injector = Guice.createInjector(
                new TestDependenciesModule(httpClient),
                new DanielModule(),
                new YleApiModule());
    }

    @After
    public void commonTearDown() throws Exception {
        verifyNoMoreInteractions(httpClient);
    }

    protected static Optional<InputStream> resource(String path) throws IOException {
        return Optional.of(Resources.getResource(path).openStream());
    }

    protected static Optional<InputStream> noResponseBody() {
        return Optional.absent();
    }

    protected OngoingStubbing<Response> whenCallingGet(String url) {
        return when(httpClient.get(eq(url), any(Multimap.class), any(Multimap.class), any(InputStream.class)));
    }

    protected static Multimap<String, String> params(String... vals) {
        Multimap<String, String> map = mapOf(vals);
        map.put("app_id", TestDependenciesModule.TESTING_APP_ID);
        map.put("app_key", TestDependenciesModule.TESTING_APP_KEY);
        return map;
    }

    protected static Multimap<String, String> mapOf(String... vals) {
        Preconditions.checkArgument(vals.length % 2 == 0, "Must provide even amount of values, was given " + vals.length);
        ListMultimap<String, String> map = MultimapBuilder.hashKeys().arrayListValues().build();
        for (int i = 0; i < vals.length;) {
            map.put(vals[i++], vals[i++]);
        }
        return map;
    }
}
