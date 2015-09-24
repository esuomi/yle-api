package io.induct.yle;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import com.ning.http.client.AsyncHttpClient;
import io.induct.daniel.DanielModule;
import io.induct.http.HttpClient;
import io.induct.http.ning.NingHttpClient;
import io.induct.rest.ApiResponse;
import io.induct.yle.api.YleApi;
import io.induct.yle.api.YleId;
import io.induct.yle.api.common.Language;
import io.induct.yle.api.media.Decrypter;
import io.induct.yle.api.media.domain.DecryptedPlayout;
import io.induct.yle.api.media.domain.EncryptedPlayout;
import io.induct.yle.api.media.domain.Playout;
import io.induct.yle.api.programs.domain.Item;
import io.induct.yle.api.programs.domain.NowPlaying;
import io.induct.yle.api.programs.domain.items.Service;
import io.induct.yle.api.programs.domain.items.TvProgram;
import io.induct.yle.ioc.YleApiModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * @since 2015-08-11
 */
public class Example {

    public static void main(String[] args) {
        try (NingHttpClient ningClient = new NingHttpClient(new AsyncHttpClient())) {
            Example example = new Example(ningClient);

            //example.runTutorial1();

            example.runTutorial_FindingOutWhatsPlayingOnTheRadio();
        }
    }

    private static final Logger log = LoggerFactory.getLogger(Example.class);

    private final YleApi api;

    private final Decrypter decrypter;

    public Example(NingHttpClient ningClient) {
        Injector injector = initialize(ningClient);
        this.api = injector.getInstance(YleApi.class);
        this.decrypter = injector.getInstance(Decrypter.class);
    }

    private void runTutorial_FindingOutWhatsPlayingOnTheRadio() {
        findRadioService();
    }

    private void findRadioService() {
        for (Service service : api.programs().listServices(Service.Type.RADIO_CHANNEL, 5, 0).getData()) {
            if (service.getId().equals("yle-radio-1")) {
                findWhatIsNowPlaying(service);
                return;
            }
        }
    }

    private void findWhatIsNowPlaying(Service service) {
        ApiResponse<NowPlaying> nowPlaying = api.programs().nowPlaying(service.getId());
    }

    private void runTutorial1() {
        tutorial1_step1();
    }

    private void tutorial1_step1() {
        ApiResponse<Item> program = api.programs().getItem(new YleId("1-820561"));

        printGenericResponse(program);
        Item item = program.getData();
        printItem(item);
        if (item instanceof TvProgram) {
            tutorial1_step2((TvProgram) item);
        } else {
            throw new RuntimeException("Something went wrong! :(");
        }
    }

    private void printItem(Item item) {
        if (item instanceof TvProgram) {
            TvProgram program = (TvProgram) item;
            log.info("Program id: {} / {} - ", program.identity(), program.getTitle(Language.FINNISH), program.getDuration());
            log.info("{}", program.getDescription(Language.FINNISH));
        }
    }

    private void tutorial1_step2(TvProgram program) {
        for (TvProgram.PublicationEvent event : program.getPublicationEvents()) {
            if (event.getTemporalStatus().equals("currently")
                    && event.getType().equals("OnDemandPublication")
                    && event.getMedia().isPresent()) {
                tutorial1_step3(program.identity(), event.getMedia().get());
                return;
            } else {
                log.info("Saw PublicationEvent {}/{}, continuing search...", event.getTemporalStatus(), event.getType());
            }
        }
        throw new RuntimeException("Could not find expected PublicationEvent!");
    }

    private void tutorial1_step3(YleId programId, TvProgram.PublicationEvent.Media media) {
        ApiResponse<List<EncryptedPlayout>> playouts = api.media().getPlayouts(programId, media.identity(), Playout.Protocol.HLS);

        printGenericResponse(playouts);
        tutorial1_step4(programId, media.identity(), playouts.getData());
    }

    private void tutorial1_step4(YleId programId, YleId mediaId, List<EncryptedPlayout> playouts) {
        for (EncryptedPlayout playout : playouts) {
            tutorial1_step5(programId, mediaId, playout);
        }
    }

    private void tutorial1_step5(YleId programId, YleId mediaId, EncryptedPlayout playout) {
        DecryptedPlayout decrypted = decrypter.decryptPlayout(playout);
        log.info("Test this stream in your favorite HLS stream player! {}", decrypted.getUrl());
        tutorial1_step6(programId, mediaId);
    }

    private void tutorial1_step6(YleId programId, YleId mediaId) {
        api.tracking().reportStreamStart(programId, mediaId);
    }

    private void printGenericResponse(ApiResponse<?> response) {
        log.info("API version: {} ", response.getApiVersion());
        log.info("Call metadata: {}", response.getMeta());
    }

    private Injector initialize(final NingHttpClient ningClient) {
        return Guice.createInjector(
                new AbstractModule() {
                    @Override
                    protected void configure() {
                        bind(HttpClient.class).toInstance(ningClient);

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
                },
                new DanielModule(),
                new YleApiModule());
    }
}
