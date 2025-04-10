package net.vansen.zylobench.benchmark;

import net.vansen.zylobench.tasks.Task;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * Benchmark utility class, used to run benchmarks
 */
public class Benchmark {

    /**
     * Runs a benchmark for the given task
     *
     * @param task The task to run
     * @param warm The number of warmup runs
     * @param runs The number of runs to perform
     * @return An array of times for each run
     */
    public static long[] runBenchmark(@NotNull Task<?> task, int warm, int runs) {
        for (int i = 0; i < warm; i++) {
            task.run();
        }

        long[] times = new long[runs];
        for (int i = 0; i < runs; i++) {
            times[i] = task.run();
        }
        Arrays.sort(times);
        return times;
    }
}
