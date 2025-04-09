package net.vansen.zylobench.benchmark;

import net.vansen.zylobench.ZyloBench;
import net.vansen.zylobench.result.Result;
import net.vansen.zylobench.utils.BasicUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Utility class for calculating and printing results
 */
@SuppressWarnings("MalformedFormatString")
public class Results {

    /**
     * Prints the result
     *
     * @param result   the result to print
     * @param timeUnit the time unit to use
     * @param maxTime  the max time
     * @param accuracy the accuracy
     */
    public static void print(@NotNull Result result, @NotNull TimeUnit timeUnit, long maxTime, int accuracy) {
        double min = BasicUtils.convert(result.min(), timeUnit);
        double avg = BasicUtils.convert(result.avg(), timeUnit);
        double max = BasicUtils.convert(result.max(), timeUnit);
        double p75 = BasicUtils.convert(result.p75(), timeUnit);
        double p99 = BasicUtils.convert(result.p99(), timeUnit);
        double top1 = BasicUtils.convert(result.top1(), timeUnit);

        ZyloBench.logger.info(String.format("\u001B[35m%s\u001B[0m", result.name()));
        ZyloBench.logger.info(String.format("  \u001B[34mMin: %10." + accuracy + "f %s %-40s\u001B[0m", min, timeUnit.toString().toLowerCase(), BasicUtils.bars(result.min(), maxTime)));
        ZyloBench.logger.info(String.format("  \u001B[34mAvg: %10." + accuracy + "f %s %-40s\u001B[0m", avg, timeUnit.toString().toLowerCase(), BasicUtils.bars(result.avg(), maxTime)));
        ZyloBench.logger.info(String.format("  \u001B[34mMax: %10." + accuracy + "f %s %-40s\u001B[0m", max, timeUnit.toString().toLowerCase(), BasicUtils.bars(result.max(), maxTime)));
        ZyloBench.logger.info(String.format("  \u001B[34mP75: %10." + accuracy + "f %s %-40s\u001B[0m", p75, timeUnit.toString().toLowerCase(), BasicUtils.bars(result.p75(), maxTime)));
        ZyloBench.logger.info(String.format("  \u001B[34mP99: %10." + accuracy + "f %s %-40s\u001B[0m", p99, timeUnit.toString().toLowerCase(), BasicUtils.bars(result.p99(), maxTime)));
        ZyloBench.logger.info(String.format("  \u001B[34mTop 1%%: %10." + accuracy + "f %s %-40s\u001B[0m", top1, timeUnit.toString().toLowerCase(), BasicUtils.bars(result.top1(), maxTime)));
    }

    /**
     * Calculates the result of a benchmark
     *
     * @param name  the name of the benchmark
     * @param times the times of the benchmark
     * @return the result
     */
    public static Result calculate(@NotNull String name, long[] times) {
        long avg = Arrays.stream(times).sum() / times.length;
        long min = times[0];
        long max = times[times.length - 1];
        long p75 = BasicUtils.percentile(times, 75);
        long p99 = BasicUtils.percentile(times, 99);
        long top1 = times[(int) (times.length * 0.99)];
        return new Result(name, min, avg, max, p75, p99, top1);
    }
}
