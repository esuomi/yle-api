package io.induct.yle.ioc;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import com.ning.http.client.AsyncHttpClient;
import io.induct.http.HttpClient;
import io.induct.http.ning.NingHttpClient;
import io.induct.yle.api.YleApi;
import io.induct.yle.api.YleId;

import java.io.IOException;

/**
 * @since 2015-05-09
 */
public class YleApiModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(HttpClient.class).toInstance(new NingHttpClient(new AsyncHttpClient()));
        bind(YleApi.class);

        SimpleModule yleApiCustomDeserializers = new SimpleModule("YleApiEntityDeserializers", new Version(1, 0, 0, null, "io.induct", "yleapi"));
        yleApiCustomDeserializers.addDeserializer(YleId.class, new JsonDeserializer<YleId>() {
            @Override
            public YleId deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
                return new YleId(jp.getText());
            }
        });
        Multibinder jacksonModules = Multibinder.newSetBinder(this.binder(), Module.class);
        jacksonModules.addBinding().toInstance(yleApiCustomDeserializers);

        bind(String.class).annotatedWith(Names.named("yle.api.appId")).toInstance("TODO_YLE_APPID");
        bind(String.class).annotatedWith(Names.named("yle.api.appKey")).toInstance("TODO_YLE_APPKEY");
    }
}
