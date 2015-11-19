package com.colinchartier.probo.math;

import com.google.common.collect.Iterables;

public class SetUnion implements Set {
    private final Set a;
    private final Set b;

    public SetUnion(Set a, Set b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Iterable<Set> getParents() {
        return Iterables.concat(a.getParents(), b.getParents());
    }
}
