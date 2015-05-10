package io.induct.yle;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.induct.yle.ioc.YleApiModule;
import org.junit.BeforeClass;

/**
 * @since 2015-05-09
 */
public abstract class YleApiTestingBase {

    protected static Injector injector;

    @BeforeClass
    public static void preSetUp() throws Exception {
        injector = Guice.createInjector(new YleApiModule());
    }
}
