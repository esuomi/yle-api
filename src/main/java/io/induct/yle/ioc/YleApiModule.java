package io.induct.yle.ioc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.util.concurrent.RateLimiter;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.multibindings.Multibinder;
import io.induct.domain.Entity;
import io.induct.yle.api.YleApi;
import io.induct.yle.api.YleId;
import io.induct.yle.api.common.Language;

import javax.inject.Named;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @since 2015-05-09
 */
public class YleApiModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(YleApi.class);

        SimpleModule yleApiCustomDeserializers = new SimpleModule("YleApiEntityDeserializers", new Version(1, 0, 0, null, "io.induct", "yleapi"));
        yleApiCustomDeserializers.addDeserializer(YleId.class, new JsonDeserializer<YleId>() {
            @Override
            public YleId deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
                return new YleId(jp.getText());
            }
        });
        yleApiCustomDeserializers.addSerializer(YleId.class, new JsonSerializer<YleId>() {
            @Override
            public void serialize(YleId value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
                gen.writeString(value.identity());
            }
        });
        yleApiCustomDeserializers.addKeyDeserializer(Language.class, new LanguageKeyDeserializer());
        yleApiCustomDeserializers.addDeserializer(Language.class, new LanguageValueDeserializer());

        yleApiCustomDeserializers.setMixInAnnotation(Entity.class, EntityIgnores.class);

        Multibinder jacksonModules = Multibinder.newSetBinder(this.binder(), Module.class);
        jacksonModules.addBinding().toInstance(yleApiCustomDeserializers);
    }

    @Provides
    @Singleton
    @Named("yle.api.rateLimit")
    RateLimiter rateLimiter(@Named("yle.api.rateLimit") String rateLimit) {
        return RateLimiter.create(Double.parseDouble(rateLimit));
    }

    private static Map<String, Language> byApiValue = createMap();

    private static Map<String, Language> createMap() {
        Map<String, Language> byApiValue = new HashMap<>(Language.values().length);
        for (Language lang : Language.values()) {
            byApiValue.put(lang.getLanguageCode(), lang);
        }
        return byApiValue;
    }

    private static class LanguageKeyDeserializer extends KeyDeserializer {

        @Override
        public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
            if (byApiValue.containsKey(key)) {
                return byApiValue.get(key);
            }
            throw new JsonMappingException("Unknown language '" + key + "'");
        }
    }

    private static class LanguageValueDeserializer extends JsonDeserializer<Language> {
        @Override
        public Language deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            Language language = byApiValue.get(p.getText());
            if (language == null)
                throw new JsonMappingException("Unknown language '" + p.getText() + "'");
            return language;
        }
    }

    private abstract class EntityIgnores {
        @JsonIgnore public abstract boolean isIdentified();

        @JsonIgnore public abstract boolean isUnidentified();
    }
}
