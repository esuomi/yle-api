package io.induct.yle;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.induct.daniel.DanielModule;
import io.induct.http.HttpClient;
import io.induct.yle.ioc.YleApiModule;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @since 2015-05-09
 */
public abstract class YleApiTestingBase {

    protected static Injector injector;

    @BeforeClass
    public static void preTestingSetUp() throws Exception {
        injector = Guice.createInjector(
                new PropertiesConfigModule(),
                new DanielModule(),
                new YleApiModule());
    }

    @AfterClass
    public static void postTestingSetup() throws Exception {
        ((AutoCloseable) injector.getInstance(HttpClient.class)).close();
    }

    protected InputStream asStream(String s) {
        return new ByteArrayInputStream(s.getBytes());
    }
}
