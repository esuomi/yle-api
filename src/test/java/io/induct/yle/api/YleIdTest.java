package io.induct.yle.api;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @since 2015-05-10
 */
public class YleIdTest {

    @Test
    public void shouldDetectProgramsType() throws Exception {
        YleId yleId = new YleId("1-1040370");
        assertTrue(yleId.is(YleId.Type.PROGRAM_OR_SERIES));
    }

    @Test
    public void shouldDetectEscenicArticleType() throws Exception {
        YleId yleId = new YleId("3-7547555");
        assertTrue(yleId.is(YleId.Type.ESCENIC));
    }

    @Test
    public void shouldDetectMediaType() throws Exception {
        YleId yleId = new YleId("6-35af958db127424fbf43694a14ece041");
        assertTrue(yleId.is(YleId.Type.MEDIA));
    }

    @Test
    public void shouldDetectSyndiArticleType() throws Exception {
        YleId yleId = new YleId("7-847937");
        assertTrue(yleId.is(YleId.Type.SYNDI));
    }

    @Test
    public void shouldDetectRadiomanSongType() throws Exception {
        YleId yleId = new YleId("12-1025-2-13834");
        assertTrue(yleId.is(YleId.Type.RADIOMAN));
    }

    @Test
    public void shouldDetectImageType() throws Exception {
        YleId yleId = new YleId("13-1234");
        assertTrue(yleId.is(YleId.Type.IMAGE));
    }

    @Test
    public void shouldDetectConceptType() throws Exception {
        YleId yleId = new YleId("18-494");
        assertTrue(yleId.is(YleId.Type.CONCEPT));
    }

    @Test
    public void shouldDetectFyndiArticleType() throws Exception {
        YleId yleId = new YleId("20-69080");
        assertTrue(yleId.is(YleId.Type.FYNDI));
    }

}
