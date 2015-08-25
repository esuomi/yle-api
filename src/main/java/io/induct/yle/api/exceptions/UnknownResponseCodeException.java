package io.induct.yle.api.exceptions;

import io.induct.yle.api.exceptions.YleApiException;

/**
 * @since 2015-08-11
 */
public class UnknownResponseCodeException extends YleApiException {
    public UnknownResponseCodeException(int statusCode) {
    }
}
