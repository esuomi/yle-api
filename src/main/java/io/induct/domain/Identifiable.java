package io.induct.domain;

/**
 * Defines an identifiable object, a.k.a Entity base requirements. For simple use cases, see {@link Entity}
 *
 * @since 2015-03-29
 */
public interface Identifiable<I> {

    I identity();

    boolean isIdentified();

    default boolean isUnidentified() {
        return !isIdentified();
    }

}