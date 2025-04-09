package net.vansen.zylobench.benchmark;

import net.vansen.zylobench.tasks.Task;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Benchmark {

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
