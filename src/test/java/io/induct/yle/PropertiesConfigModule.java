package io.induct.yle;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Read in the needed configs to run tests.
 *
 * @since 2015-05-23
 */
public class PropertiesConfigModule extends AbstractModule {

    private final Logger log = LoggerFactory.getLogger(PropertiesConfigModule.class);

    @Override
    protected void configure() {
        String fileName = "apikeys.properties";
        File file = new File(fileName);
        try {
            Properties props = new Properties();
            props.load(new FileInputStream(file));
            for (String key : props.stringPropertyNames()) {
                log.debug("Binding '{}'", key);
                bind(String.class).annotatedWith(Names.named(key)).toInstance(props.getProperty(key));
            }
        } catch (IOException e) {
            log.warn("Failed to read file '{}'", fileName, e);
        }
    }
}
