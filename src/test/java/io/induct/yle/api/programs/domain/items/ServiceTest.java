package io.induct.yle.api.programs.domain.items;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import io.induct.rest.ApiResponse;
import io.induct.yle.api.YleDomainEntityTestBase;
import io.induct.yle.api.common.Language;
import org.joda.time.DateTime;
import org.junit.Ignore;

import java.util.List;

/**
 * @since 2015-09-11
 */
@Ignore("test not fully ready yet")
public class ServiceTest extends YleDomainEntityTestBase<List<Service>> {

    @Override
    protected TypeReference<ApiResponse<List<Service>>> typeDefinition() {
        return new TypeReference<ApiResponse<List<Service>>>() {};
    }

    @Override
    protected String testResource() {
        return "api/v1/programs/radiochannel_services.json";
    }

    @Override
    protected List<Service> expectedDomainEntity() {
        return Lists.newArrayList(new Service(
                "RadioContent",
                new Service.Homepage(
                        localized(Language.FINNISH, "Kotisivu", Language.SWEDISH, "Hemsida"),
                        "WebPage",
                        "http://yle.fi/radio1",
                        "http://yle.fi"
                ),
                new DateTime("2015-06-12T08:38:55.096Z"),
                "RadioChannel",
                Lists.newArrayList(
                        new Service.Interaction(
                                localized(Language.FINNISH, "Twitter"),
                                Lists.newArrayList(
                                        new Service.Interaction.HasVersion(
                                                localized(Language.FINNISH, "Twitter"),
                                                "mobile",
                                                "WebPage",
                                                "https://mobile.twitter.com/yleradio1",
                                                "http://twitter.com"
                                        )
                                ),
                                "WebPage",
                                "https://mobile.twitter.com/yleradio1",
                                "http://twitter.com"
                        ),
                        new Service.Interaction(
                                localized(Language.FINNISH, "Facebook"),
                                Lists.newArrayList(
                                        new Service.Interaction.HasVersion(
                                                localized(Language.FINNISH, "Facebook"),
                                                "mobile",
                                                "WebPage",
                                                "https://m.facebook.com/yleradio1",
                                                "http://facebook.com"
                                        )
                                ),
                                "WebPage",
                                "https://facebook.com/yleradio1",
                                "http://facebook.com"
                        )
                ),
                localized(Language.FINNISH, "Yle Radio 1"),
                "Finland",
                Lists.newArrayList(
                        new Service.ProgramGuide(
                                localized(Language.FINNISH, "Ohjelmaopas", Language.SWEDISH, "Programguide"),
                                "WebPage",
                                "http://yle.fi",
                                "http://ohjelmaopas.yle.fi/radio/opas"
                        )
                ),
                Lists.newArrayList(
                        new Service.Outlet(
                                "Finland",
                                "Webcast"
                        )
                ),
                Lists.newArrayList(Language.FINNISH),
                true,
                "yle-radio-1",
                1000,
                "Channel",
                Lists.newArrayList(
                        new Audio(
                                Lists.newArrayList(Language.FINNISH),
                                Lists.newArrayList(
                                        new Audio.Format(
                                                "mediaformat-classification",
                                                "Concept",
                                                "stereo"
                                        )
                                ),
                                "AudioTrack"
                        )
                ))
        );
    }
}