package io.induct.yle.api.programs.domain.items;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import io.induct.daniel.Daniel;
import io.induct.rest.ApiResponse;
import io.induct.yle.YleApiTestingBase;
import io.induct.yle.api.YleId;
import io.induct.yle.api.common.Language;
import io.induct.yle.api.programs.domain.items.TvProgram.Audio;
import io.induct.yle.api.programs.domain.items.TvProgram.ContentRating;
import io.induct.yle.api.programs.domain.items.TvProgram.Creator;
import io.induct.yle.api.programs.domain.items.TvProgram.Image;
import io.induct.yle.api.programs.domain.items.TvProgram.PublicationEvent;
import io.induct.yle.api.programs.domain.items.TvProgram.Season;
import io.induct.yle.api.programs.domain.items.TvProgram.Series;
import io.induct.yle.api.programs.domain.items.TvProgram.Subject;
import io.induct.yle.api.programs.domain.items.TvProgram.Subtitling;
import io.induct.yle.api.programs.domain.items.TvProgram.Video;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @since 2015-08-04
 */
public class TvProgramTest extends YleApiTestingBase {

    private Daniel daniel;

    @Before
    public void setUp() throws Exception {
        daniel = injector.getInstance(Daniel.class);
    }

    private final TypeReference<ApiResponse<TvProgram>> tvProgram = new TypeReference<ApiResponse<TvProgram>>() {
    };

    @Test
    public void shouldDeserializeFromExampleApiResponse() throws Exception {
        TvProgram expectedLovejoyEpisode = new TvProgram(
                new YleId("1-2048985"),
                localized(
                        Language.FINNISH, "Kausi 5, 9/13. Kiinalainen kanuuna. Lovejoy ja Charlotte joutuvat vaaraan vanhan \"kukkaruukun\" takia.",
                        Language.SWEDISH, "Kinesisk kanon. Lovejoy och Charlotte råkar i fara pga en gammal \"blomkruka\". Stereo. Repris."),
                Arrays.asList(new Creator("Fremantle International Television", "Organization")),
                new Season(
                        localized(Language.FINNISH, "Antiikkikauppias Lovejoyta ei jymäytetä. Alalla liikkuu raha, ja se houkuttaa mukaan vilunkiväkeä, mutta mies kyllä tunnistaa aidon aarteen tusinatavarasta. Ja minne hän ei ehdi itse, sinne tulevat ystävät avuksi."),
                        1,
                        Arrays.asList(new Creator("Fremantle International Television", "Organization")),
                        ImmutableMap.of("id", new YleId("1-2660452")),
                        new DateTime("2015-06-17T12:32:35.197Z", DateTimeZone.UTC),
                        localized(Language.FINNISH, "Lovejoy", Language.SWEDISH, "Lovejoy"),
                        Arrays.asList("Britannia"),
                        new YleId("1-2664234"),
                        Arrays.asList(
                                new Subject(new YleId("5-134"), localized(Language.FINNISH, "Ulkomaiset sarjat", Language.SWEDISH, "Utländska serier"), Optional.of(ImmutableMap.of("id", new YleId("5-131"))), "areena-content-classification", "Concept", Optional.of("ulkomaisetsarjat"), Optional.absent()),
                                new Subject(new YleId("5-262"), localized(Language.FINNISH, "Draama"), Optional.absent(), "areena-analytics-classification", "Concept", Optional.of("draama"), Optional.absent()),
                                new Subject(new YleId("21-7"), localized(Language.FINNISH, "Ulkomainen fiktio", Language.ENGLISH, "Foreign Fiction"), Optional.absent(), "finnpanel-genre-classification", "Concept", Optional.absent(), Optional.of(Arrays.asList(new Subject.Notation("6", "finnpanel-notation")))))),
                new Series(
                        new YleId("1-2660452"),
                        localized(Language.FINNISH, "Antiikkikauppias Lovejoyta ei jymäytetä. Alalla liikkuu raha, ja se houkuttaa mukaan vilunkiväkeä, mutta mies kyllä tunnistaa aidon aarteen tusinatavarasta. Ja minne hän ei ehdi itse, sinne tulevat ystävät avuksi."),
                        Arrays.asList(new Creator("Fremantle International Television", "Organization")),
                        new DateTime("2015-06-17T12:32:35.175Z", DateTimeZone.UTC),
                        localized(Language.FINNISH, "Lovejoy", Language.SWEDISH, "Lovejoy"),
                        new Image(new YleId("13-1-2660452-cover"), true, "ImageObject"),
                        Arrays.asList(new Season(
                                localized(Language.FINNISH, "Antiikkikauppias Lovejoyta ei jymäytetä. Alalla liikkuu raha, ja se houkuttaa mukaan vilunkiväkeä, mutta mies kyllä tunnistaa aidon aarteen tusinatavarasta. Ja minne hän ei ehdi itse, sinne tulevat ystävät avuksi."),
                                1,
                                Arrays.asList(new Creator("Fremantle International Television", "Organization")),
                                ImmutableMap.of("id", new YleId("1-2660452")),
                                new DateTime("2015-06-17T12:32:35.197Z", DateTimeZone.UTC),
                                localized(Language.FINNISH, "Lovejoy", Language.SWEDISH, "Lovejoy"),
                                Arrays.asList("Britannia"),
                                new YleId("1-2664234"),
                                Arrays.asList(
                                        new Subject(new YleId("5-134"), localized(Language.FINNISH, "Ulkomaiset sarjat", Language.SWEDISH, "Utländska serier"), Optional.of(ImmutableMap.of("id", new YleId("5-131"))), "areena-content-classification", "Concept", Optional.of("ulkomaisetsarjat"), Optional.absent()),
                                        new Subject(new YleId("5-262"), localized(Language.FINNISH, "Draama"), Optional.absent(), "areena-analytics-classification", "Concept", Optional.of("draama"), Optional.absent()),
                                        new Subject(new YleId("21-7"), localized(Language.FINNISH, "Ulkomainen fiktio", Language.ENGLISH, "Foreign Fiction"), Optional.absent(), "finnpanel-genre-classification", "Concept", Optional.absent(), Optional.of(Arrays.asList(new Subject.Notation("6", "finnpanel-notation"))))))),
                        Arrays.asList("Britannia"),
                        Arrays.asList(
                                new Subject(new YleId("5-134"), localized(Language.FINNISH, "Ulkomaiset sarjat", Language.SWEDISH, "Utländska serier"), Optional.of(ImmutableMap.of("id", new YleId("5-131"))), "areena-content-classification", "Concept", Optional.of("ulkomaisetsarjat"), Optional.absent()),
                                new Subject(new YleId("5-262"), localized(Language.FINNISH, "Draama"), Optional.absent(), "areena-analytics-classification", "Concept", Optional.of("draama"), Optional.absent()),
                                new Subject(new YleId("21-7"), localized(Language.FINNISH, "Ulkomainen fiktio", Language.ENGLISH, "Foreign Fiction"), Optional.absent(), "finnpanel-genre-classification", "Concept", Optional.absent(), Optional.of(Arrays.asList(new Subject.Notation("6", "finnpanel-notation")))))),
                new Video(Arrays.asList(new Video.Format("mediaformat-classification", "Concept", "4:3")), "VideoTrack"),
                "TVContent",
                57,
                localized(Language.FINNISH, "Kiinalainen kanuuna saattaa Lovejoyn vaaraan"),
                new DateTime("2015-08-04T06:20:04.705Z", DateTimeZone.UTC),
                Arrays.asList(new YleId("16-0-0212594")),
                "PT48M57S",
                "43153257000",
                new ContentRating(
                        localized(Language.FINNISH, "Sallittu yli 7-vuotiaille", Language.SWEDISH, "Förbjudet under 7 år", Language.ENGLISH, "Not for persons under 7"),
                        7,
                        Arrays.asList(
                                new ContentRating.Reason(
                                        localized(Language.FINNISH, "Voi aiheuttaa ahdistusta", Language.SWEDISH, "Kan skapa ångest", Language.ENGLISH, "Content may cause anxiety"),
                                        "ContentRatingReason",
                                        "anxiety"),
                                new ContentRating.Reason(
                                        localized(Language.FINNISH, "Sisältää väkivaltaa", Language.SWEDISH, "Våldsinslag", Language.ENGLISH, "Violent content"),
                                        "ContentRatingReason",
                                        "violence"
                                ))),
                localized(Language.FINNISH, "Lovejoy", Language.SWEDISH, "Lovejoy"),
                Arrays.asList("Britannia"),
                "Program",
                new Image(new YleId("13-1-2048985"), true, "ImageObject"),
                Arrays.asList(new Audio(Arrays.asList(Language.ENGLISH), Arrays.asList(new Audio.Format("mediaformat-classification", "Concept", "stereo")), "AudioTrack")),
                localized(Language.UNKNOWN, "Lovejoy"),
                Arrays.asList(
                        new PublicationEvent(
                                new PublicationEvent.Service("yle-areena"),
                                Optional.of(Arrays.asList(new PublicationEvent.Publisher("yle-areena"))),
                                new DateTime("2015-08-04T06:00:00Z", DateTimeZone.UTC),
                                "currently",
                                new DateTime("2015-10-30T21:59:59Z", DateTimeZone.UTC),
                                "OnDemandPublication",
                                "P2M26DT14H59M59S",
                                "Finland",
                                new YleId("4-4472094"),
                                Optional.of(new PublicationEvent.Media(
                                        new YleId("6-894a7ec0b6b149d68ffb310a169320ce"),
                                        "PT48M57S",
                                        Arrays.asList(new PublicationEvent.Media.ContentProtection(new YleId("22-1"), "ContentProtectionPolicy"))))),
                        new PublicationEvent(
                                new PublicationEvent.Service("yle-tv1"),
                                Optional.absent(),
                                new DateTime("2005-01-08T13:10:14Z", DateTimeZone.UTC),
                                "in-past",
                                new DateTime("2005-01-08T13:59:11Z", DateTimeZone.UTC),
                                "ScheduledTransmission",
                                "PT48M57S",
                                "Finland",
                                new YleId("4-4135961"),
                                Optional.of(new PublicationEvent.Media(null, null, null))), // TODO: This should be Optional.absent() but the API has empty object which deserializes like this. Needs to be fixed obviously.
                        new PublicationEvent(
                                new PublicationEvent.Service("yle-tv1"),
                                Optional.absent(),
                                new DateTime("2003-10-10T12:05:13Z", DateTimeZone.UTC),
                                "in-past",
                                new DateTime("2003-10-10T12:54:10Z", DateTimeZone.UTC),
                                "ScheduledTransmission",
                                "PT48M57S",
                                "Finland",
                                new YleId("4-4135962"),
                                Optional.of(new PublicationEvent.Media(null, null, null)))),
                "main",
                Arrays.asList(
                        new Subject(new YleId("5-142"), localized(Language.FINNISH, "Huumori", Language.SWEDISH, "Humor"), Optional.of(ImmutableMap.of("id", new YleId("5-139"))), "areena-content-classification", "Concept", Optional.of("huumori"), Optional.absent()),
                        new Subject(new YleId("5-136"), localized(Language.FINNISH, "Komedia", Language.SWEDISH, "Komedi"), Optional.of(ImmutableMap.of("id", new YleId("5-131"))), "areena-content-classification", "Concept", Optional.of("komedia"), Optional.absent()),
                        new Subject(new YleId("5-131"), localized(Language.FINNISH, "Sarjat ja elokuvat", Language.SWEDISH, "Serier och film"), Optional.of(ImmutableMap.of("id", new YleId("5-130"))), "areena-content-classification", "Concept", Optional.of("sarjatjaelokuvat"), Optional.absent()),
                        new Subject(new YleId("5-134"), localized(Language.FINNISH, "Ulkomaiset sarjat", Language.SWEDISH, "Utländska serier"), Optional.of(ImmutableMap.of("id", new YleId("5-131"))), "areena-content-classification", "Concept", Optional.of("ulkomaisetsarjat"), Optional.absent()),
                        new Subject(new YleId("5-262"), localized(Language.FINNISH, "Draama"), Optional.absent(), "areena-analytics-classification", "Concept", Optional.of("draama"), Optional.absent()),
                        new Subject(new YleId("21-7"), localized(Language.FINNISH, "Ulkomainen fiktio", Language.ENGLISH, "Foreign Fiction"), Optional.absent(), "finnpanel-genre-classification", "Concept", Optional.absent(), Optional.of(Arrays.asList(new Subject.Notation("6", "finnpanel-notation"))))
                ),
                Arrays.asList(
                        new Subtitling(Arrays.asList(Language.FINNISH), "Subtitling")
                )
        );

        ApiResponse<TvProgram> item = daniel.deserialize(tvProgram, resource("api/v1/programs/items/1-2048985.json").get());
        TvProgram program = item.getData();

        assertThat(program, is(equalTo(expectedLovejoyEpisode)));
    }

    private static Map<Language, String> localized(Object... vals) {
        Map<Language, String> map = Maps.newLinkedHashMap();
        for (int i = 0; i < vals.length; ) {
            map.put((Language) vals[i++], (String) vals[i++]);
        }
        return map;
    }
}