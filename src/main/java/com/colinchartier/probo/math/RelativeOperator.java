package com.colinchartier.probo.math;

import com.google.common.collect.ImmutableMap;

import java.util.Optional;

public enum RelativeOperator {
    GREATER_THAN(">"),
    GREATER_THAN_OR_EQUAL_TO(">="),
    EQUAL_TO("="),
    LESS_THAN("<"),
    LESS_THAN_OR_EQUAL_TO("<=");

    static final ImmutableMap<String, RelativeOperator> identifierMap = populateIdentifierMap();

    private static ImmutableMap<String, RelativeOperator> populateIdentifierMap() {
        ImmutableMap.Builder<String, RelativeOperator> b = ImmutableMap.builder();
        for(RelativeOperator v : values()) {
            b.put(v.getIdentifier(), v);
        }
        return b.build();
    }

    private final String identifier;

    RelativeOperator(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier( ) {
        return identifier;
    }

    public static Optional<RelativeOperator> getRelativeOperator(String identifier) {
        return Optional.ofNullable(identifierMap.get(identifier));
    }
}
