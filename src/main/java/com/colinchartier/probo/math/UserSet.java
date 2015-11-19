package com.colinchartier.probo.math;

import com.google.common.collect.Iterables;

import java.util.Collections;

public class UserSet implements Set {
    private final String identifier;
    private final Set universe;

    public UserSet(String identifier, Set universe) {
        this.identifier = identifier;
        this.universe = universe;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public Iterable<Set> getParents() {
        return Iterables.concat(universe.getParents(), Collections.singleton(universe));
    }
}
