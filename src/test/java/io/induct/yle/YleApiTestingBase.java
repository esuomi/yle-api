package io.induct.yle;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.induct.daniel.DanielModule;
import io.induct.yle.ioc.YleApiModule;
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
                new DanielModule(),
                new YleApiModule());
    }

    protected InputStream asStream(String s) {
        return new ByteArrayInputStream(s.getBytes());
    }
}
