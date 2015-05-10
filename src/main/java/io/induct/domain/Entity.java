package io.induct.domain;

/**
 * Immutable base implementation for {@link Identifiable}. Uses default fallback value for detecting identification
 * state.
 *
 * @since 2015-03-29
 */
public abstract class Entity<I> implements Identifiable<I> {
    private final I identity;
    private final I unidentifiedIdentity;

    protected Entity(I identity, I unidentifiedIdentity) {
        this.identity = identity;
        this.unidentifiedIdentity = unidentifiedIdentity;
    }

    @Override
    public I identity() {
        return identity;
    }

    @Override
    public boolean isIdentified() {
        return !isUnidentified();
    }

    @Override
    public boolean isUnidentified() {
        return identity.equals(unidentifiedIdentity);
    }
}