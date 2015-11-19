package com.colinchartier.probo.math;

import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum BaseSet implements Set{
    REALS("R") {
        @Override
        public Iterable<Set> getParents() {
            return Collections.emptyList(); //In this program, the real numbers are the largest universe.
        }
    },
    RATIONALS("Q") {
        @Override
        public Iterable<Set> getParents() {
            //TODO propositional sets
            return Iterables.concat(REALS.getParents(), Collections.singleton(REALS));
        }
    },
    INTEGERS("Z") {
        @Override
        public Iterable<Set> getParents() {
            //TODO propositional sets
            return Iterables.concat(REALS.getParents(), Collections.singleton(REALS));
        }
    },
    NATURALS("N") {
        @Override
        public Iterable<Set> getParents() {
            //TODO propositional sets
            return Iterables.concat(INTEGERS.getParents(), Collections.singleton(INTEGERS));
        }

        //TODO some sort of implicit propositions, I.E all x in N >= 0
    };

    private final String symbol;

    BaseSet(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol( ) {
        return symbol;
    }
}
