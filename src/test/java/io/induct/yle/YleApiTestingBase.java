package io.induct.yle;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @since 2015-05-09
 */
public abstract class YleApiTestingBase {

    protected InputStream asStream(String s) {
        return new ByteArrayInputStream(s.getBytes());
    }
}
