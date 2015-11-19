package com.colinchartier.probo.math;

public interface Set {
    /**
     * @return all sets that contain more numbers than this one.
     */
    Iterable<Set> getParents(); //TODO list of propositions, I.E Z would have parent N if all elements were >= 0.
}
