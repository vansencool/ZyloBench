package net.vansen.zylobench.utils;

import net.vansen.zylobench.ZyloBench;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

/**
 * Basic utils for ZyloBench (these are really just one line methods)
 */
public class BasicUtils {

    /**
     * Logs a nice line break
     */
    public static void printBreak() {
        ZyloBench.logger.info("\u001B[36m-----------------------------------------------------------------------------------\u001B[0m");
    }

    /**
     * Returns a nice bar graph
     *
     * @param value  value to graph
     * @param maxVal max value
     * @return bar graph
     */
    public static String bars(long value, long maxVal) {
        return "\u001B[32m" + "█".repeat(Math.max(1, (int) (40 * ((double) value / maxVal)))) + "\u001B[0m";
    }

    /**
     * Returns the percentile of a sorted array
     *
     * @param sortedTimes sorted array
     * @param percentile  percentile
     * @return percentile
     */
    public static long percentile(long[] sortedTimes, double percentile) {
        return sortedTimes[(int) Math.ceil(sortedTimes.length * percentile / 100.0) - 1];
    }

    /**
     * Converts a nano time to the given unit
     *
     * @param time nano time
     * @param unit unit to convert to
     * @return converted time
     */
    public static double convert(long time, @NotNull TimeUnit unit) {
        return (double) time / unit.toNanos(1);
    }
}
