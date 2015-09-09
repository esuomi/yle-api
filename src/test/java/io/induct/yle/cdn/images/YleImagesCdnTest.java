package io.induct.yle.cdn.images;

import com.cloudinary.Transformation;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.induct.yle.YleApiTestingBase;
import io.induct.yle.api.YleId;
import io.induct.yle.cdn.images.domain.Format;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @since 2015-09-09
 */
public class YleImagesCdnTest extends YleApiTestingBase {

    private YleImagesCdn imagesCdn;
    private Splitter atSlashes = Splitter.on('/').omitEmptyStrings();
    private Splitter atCommas = Splitter.on(',');

    @Before
    public void setUp() throws Exception {
        imagesCdn = injector.getInstance(YleImagesCdn.class);
    }

    @Test
    public void shouldProduceValidCdnUrlForValidParameters() throws Exception {
        YleId imageId = new YleId("13-1-2185369");
        String url = imagesCdn.urlFor(imageId, Format.JPEG);
        assertEquals(url, "http://yle.cdn/image/upload/13-1-2185369.jpg");
    }

    @Test
    public void shouldAddTransformationsAsMidfixIfPresent() throws Exception {
        YleId imageId = new YleId("13-1-2185369");
        String url = imagesCdn.urlFor(imageId, Format.JPEG, Optional.of(new Transformation().width(120).height(120).crop("fit")));

        // NOTE: We need to extract the params as Cloudinary's Transform class uses HashMaps making the params order
        // independent.
        Set<String> appliedTransformations = Sets.newHashSet(
                atCommas.split(Lists.newArrayList(atSlashes.split(url)).get(4)));
        assertEquals(appliedTransformations.size(), 3);
        assertTrue(appliedTransformations.contains("c_fit"));
        assertTrue(appliedTransformations.contains("w_120"));
        assertTrue(appliedTransformations.contains("h_120"));
    }
}