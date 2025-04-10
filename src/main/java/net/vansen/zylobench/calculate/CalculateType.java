package net.vansen.zylobench.calculate;

import net.vansen.zylobench.result.Result;
import org.jetbrains.annotations.NotNull;

/**
 * Calculate type, which is used to calculate the speedup between fastest and other results for summary
 */
public enum CalculateType {

    /**
     * Calculates based on minimum time
     */
    MIN,

    /**
     * Calculates based on average time
     */
    AVG,

    /**
     * Calculates based on maximum time
     */
    MAX,

    /**
     * Calculates based on 75th percentile
     */
    P75,

    /**
     * Calculates based on 99th percentile
     */
    P99,

    /**
     * Calculates based on top 1%
     */
    TOP1,

    /**
     * Calculates based on total time
     */
    TOTAL;

    /**
     * The speedup between two results
     *
     * @param current the result to compare
     * @param fastest the fastest result
     * @return the speedup
     */
    public double speedup(@NotNull Result current, @NotNull Result fastest) {
        return switch (this) {
            case MIN -> (double) current.min() / fastest.min();
            case AVG -> (double) current.avg() / fastest.avg();
            case MAX -> (double) current.max() / fastest.max();
            case P75 -> (double) current.p75() / fastest.p75();
            case P99 -> (double) current.p99() / fastest.p99();
            case TOP1 -> (double) current.top1() / fastest.top1();
            case TOTAL -> (double) current.total() / fastest.total();
        };
    }
}