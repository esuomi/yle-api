package io.induct.yle.cdn.images;

import com.cloudinary.Transformation;
import com.google.common.base.Optional;
import io.induct.yle.api.YleId;
import io.induct.yle.cdn.images.domain.Format;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @since 2015-09-09
 */
public class YleImagesCdn {
    private final String imageCdnBaseUrl;

    @Inject
    public YleImagesCdn(@Named("yle.images.baseUrl") String imageCdnBaseUrl) {
        this.imageCdnBaseUrl = imageCdnBaseUrl;
    }

    public String urlFor(YleId imageId, Format format) {
        return urlFor(imageId, format, Optional.absent());
    }

    public String urlFor(YleId imageId, Format format, Optional<Transformation> transformation) {
        StringBuilder url = new StringBuilder();
        url.append(imageCdnBaseUrl).append("/image/upload");
        if (transformation.isPresent()) {
            url.append("/").append(transformation.get().generate());
        }
        url.append("/").append(imageId.identity()).append(".").append(format.getApiValue());
        return url.toString();
    }
}
