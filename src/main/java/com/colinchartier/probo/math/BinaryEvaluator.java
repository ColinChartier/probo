package com.colinchartier.probo.math;

import java.math.BigDecimal;

public interface BinaryEvaluator {
    BigDecimal evaluate(BigDecimal left, BigDecimal right);
}
