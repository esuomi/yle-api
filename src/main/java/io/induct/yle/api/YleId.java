package io.induct.yle.api;

import com.google.common.collect.ImmutableMap;
import io.induct.domain.Identifiable;

/**
 * @since 2015-05-10
 */
public class YleId implements Identifiable<String> {

    public static final YleId UNIDENTIFIED = new YleId("");

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

    @Override
    public String identity() {
        return identity;
    }

    @Override
    public boolean isIdentified() {
        return !type.equals(Type.UNKNOWN);
    }
}
