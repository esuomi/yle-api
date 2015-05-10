package io.induct.yle.ioc;

import com.google.inject.AbstractModule;
import io.induct.yle.api.YleApi;

/**
 * @since 2015-05-09
 */
public class YleApiModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(YleApi.class);
    }
}
