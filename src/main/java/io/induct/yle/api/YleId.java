package io.induct.yle.api;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;
import io.induct.domain.Identifiable;

import java.io.Serializable;

/**
 * @since 2015-05-10
 */
public class YleId implements Identifiable<String>, Serializable, Comparable<YleId> {

    public static final YleId UNIDENTIFIED = new YleId("");

    @Override
    public int compareTo(YleId that) {
        return this.identity.compareTo(that.identity);
    }

    public enum Type {
        ESCENIC("3-"),
        PROGRAM_OR_SERIES("1-"),
        MEDIA("6-"),
        SYNDI("7-"),
        RADIOMAN("12-"),
        IMAGE("13-"),
        CONCEPT("18-"),
        FYNDI("20-"),
        UNKNOWN("");

        private final String prefix;

        Type(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return prefix;
        }

    }
    private static final ImmutableMap<String, Type> prefixLookup = buildPrefixLookup();

    private static ImmutableMap<String, Type> buildPrefixLookup() {
        ImmutableMap.Builder<String, Type> builder = ImmutableMap.builder();
        for (Type type : Type.values()) {
            builder.put(type.prefix, type);
        }
        return builder.build();
    }

    private final Type type;
    private final String identity;

    public YleId(String id) {
        if (!id.contains("-")) {
            this.type = Type.UNKNOWN;
        } else {
            String prefix = id.substring(0, id.indexOf('-') + 1);
            this.type = prefixLookup.containsKey(prefix) ? prefixLookup.get(prefix) : Type.UNKNOWN;
        }
        this.identity = id;
    }

    public boolean is(Type type) {
        return type.equals(type);
    }

    public Type getType() {
        return type;
    }

    @JsonGetter("id")
    @Override
    public String identity() {
        return identity;
    }

    @Override
    public boolean isIdentified() {
        return !identity.equals(UNIDENTIFIED);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(identity);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final YleId other = (YleId) obj;
        return Objects.equal(this.identity, other.identity);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("type", type)
                .add("identity", identity)
                .toString();
    }
}
