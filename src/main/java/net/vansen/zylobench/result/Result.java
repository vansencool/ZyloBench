package net.vansen.zylobench.result;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a result of a benchmark
 *
 * @param name The name of the benchmark
 * @param min  The minimum time
 * @param avg  The average time
 * @param max  The maximum time
 * @param p75  The 75th percentile
 * @param p99  The 99th percentile
 * @param top1 The time of the top 1%
 */
public record Result(@NotNull String name, long min, long avg, long max, long p75, long p99, long top1) {
}