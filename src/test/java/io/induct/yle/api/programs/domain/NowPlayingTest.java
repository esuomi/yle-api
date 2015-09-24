package io.induct.yle.api.programs.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import io.induct.rest.ApiResponse;
import io.induct.yle.api.YleDomainEntityTestBase;
import io.induct.yle.api.YleId;
import io.induct.yle.api.common.Language;
import io.induct.yle.api.programs.domain.items.Audio;
import io.induct.yle.api.programs.domain.items.Service;
import org.joda.time.DateTime;
import org.junit.Ignore;

/**
 * @since 2015-09-11
 */
@Ignore("this test is incomplete")
public class NowPlayingTest extends YleDomainEntityTestBase<NowPlaying> {

    @Override
    protected TypeReference<ApiResponse<NowPlaying>> typeDefinition() {
        return new TypeReference<ApiResponse<NowPlaying>>() {};
    }

    @Override
    protected String testResource() {
        return "api/v1/programs/nowplaying.json";
    }

    @Override
    protected NowPlaying expectedDomainEntity() {
        return new NowPlaying(
                new DateTime(),
                new DateTime(),
                "foobar",
                new NowPlaying.Content(
                        new YleId(""),
                        "type",
                        localized(Language.FINNISH, ""),
                        Lists.newArrayList(new NowPlaying.Content.Performer("type", "name")),
                        localized(Language.FINNISH, "")
                ),
                new Service(
                        "typeMedia",
                        new Service.Homepage(
                                localized(Language.FINNISH, ""),
                                "type",
                                "url",
                                "serviceProvider"
                        ),
                        new DateTime(),
                        "type",
                        Lists.newArrayList(
                                new Service.Interaction(
                                        localized(Language.FINNISH, ""),
                                        Lists.newArrayList(
                                                new Service.Interaction.HasVersion(
                                                        localized(Language.FINNISH, ""),
                                                        "device",
                                                        "type",
                                                        "url",
                                                        "serviceProvider"
                                                )
                                        ),
                                        "type",
                                        "url",
                                        "serviceProvider"
                                )
                        ),
                        localized(Language.FINNISH, ""),
                        "region",
                        Lists.newArrayList(
                                new Service.ProgramGuide(
                                        localized(Language.FINNISH, ""),
                                        "type",
                                        "url",
                                        "serviceProvider"
                                )
                        ),
                        Lists.newArrayList(new Service.Outlet("region", "type")),
                        Lists.newArrayList(Language.FINNISH),
                        true,
                        "id",
                        1,
                        "typeCreative",
                        Lists.newArrayList(
                                new Audio(
                                        Lists.newArrayList(Language.FINNISH),
                                        Lists.newArrayList(
                                                new Audio.Format("inScheme", "type", "key")
                                        ),
                                        "type"
                                )
                        )
                ),
                "delta",
                new NowPlaying.PartOf(new YleId(""), "typr", localized(Language.FINNISH, ""))
        );
    }
}