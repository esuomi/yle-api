package io.induct.yle;

import com.google.common.base.Optional;
import com.google.common.io.Resources;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.induct.daniel.DanielModule;
import io.induct.http.HttpClient;
import io.induct.yle.ioc.YleApiModule;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.InputStream;

import static org.mockito.Mockito.verifyNoMoreInteractions;

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
}
