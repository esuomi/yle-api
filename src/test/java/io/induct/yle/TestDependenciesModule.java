package io.induct.yle;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import io.induct.http.HttpClient;

/**
 * Read in the needed configs to run tests.
 *
 * @since 2015-05-23
 */
public class TestDependenciesModule extends AbstractModule {

    public static final String TESTING_BASE_URL = "http://yle.api";
    public static final String TESTING_RATE_LIMIT = "100";
    public static final String TESTING_APP_ID = "TEST_APP_ID";
    public static final String TESTING_APP_KEY = "TEST_APP_KEY";
    public static final String TESTING_STREAM_KEY = "TEST_STREAM_KEY";

    private final HttpClient httpClient;

    public TestDependenciesModule(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    protected void configure() {
        bind(HttpClient.class).toInstance(httpClient);

        bind(String.class).annotatedWith(Names.named("yle.api.baseUrl")).toInstance(TESTING_BASE_URL);
        bind(String.class).annotatedWith(Names.named("yle.api.rateLimit")).toInstance(TESTING_RATE_LIMIT);
        bind(String.class).annotatedWith(Names.named("yle.api.appId")).toInstance(TESTING_APP_ID);
        bind(String.class).annotatedWith(Names.named("yle.api.appKey")).toInstance(TESTING_APP_KEY);
        bind(String.class).annotatedWith(Names.named("yle.api.streamKey")).toInstance(TESTING_STREAM_KEY);
    }
}
