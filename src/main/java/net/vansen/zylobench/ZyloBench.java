package net.vansen.zylobench;

import net.vansen.zylobench.benchmark.Benchmark;
import net.vansen.zylobench.benchmark.Results;
import net.vansen.zylobench.calculate.CalculateType;
import net.vansen.zylobench.result.Result;
import net.vansen.zylobench.summary.Summary;
import net.vansen.zylobench.tasks.Advanced;
import net.vansen.zylobench.tasks.Normal;
import net.vansen.zylobench.tasks.Task;
import net.vansen.zylobench.tasks.builder.AdvancedBuilder;
import net.vansen.zylobench.tasks.builder.NormalBuilder;
import net.vansen.zylobench.utils.BasicUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ZyloBench, a benchmarking tool for Java
 * <p>
 * Reminder, this benchmarking tool is NOT meant for nanosecond accuracy, even though it tries to have no overhead, it is still not guaranteed to be nanosecond accurate at all.
 */
@SuppressWarnings({"unused"})
public class ZyloBench {

    public static final Logger logger = LoggerFactory.getLogger(ZyloBench.class);

    private int warm = 10;
    private int runs = 100;
    private final List<Task<?>> tasks = new ArrayList<>();
    private TimeUnit timeUnit = TimeUnit.MICROSECONDS;
    private int accuracy = 2;
    private CalculateType calculateType = CalculateType.AVG;

    /**
     * Returns a new ZyloBench
     *
     * @return A new ZyloBench
     */
    public static ZyloBench make() {
        return new ZyloBench();
    }

    /**
     * Makes a new advanced task builder
     *
     * @param t   the type of data
     * @param <T> The type of data
     * @return A new advanced task builder
     */
    public static <T> AdvancedBuilder<T> advanced(@Nullable T t) {
        return new AdvancedBuilder<>();
    }

    /**
     * Makes a new normal task builder
     *
     * @return A new normal task builder
     */
    public static NormalBuilder normal() {
        return new NormalBuilder();
    }

    /**
     * How many times to warm up the benchmark
     *
     * @param w The number of warmups
     * @return The ZyloBench
     */
    public ZyloBench warm(int w) {
        this.warm = w;
        return this;
    }

    /**
     * How many times to run the benchmark
     *
     * @param r The number of runs
     * @return The ZyloBench
     */
    public ZyloBench runs(int r) {
        this.runs = r;
        return this;
    }

    /**
     * Adds a normal task (benchmark) to the ZyloBench
     *
     * @param norm The normal task
     * @return The ZyloBench
     */
    public ZyloBench add(@NotNull Normal norm) {
        tasks.add(new Task<>(norm));
        return this;
    }

    /**
     * Adds an advanced task (benchmark) to the ZyloBench
     *
     * @param adv The advanced task
     * @return The ZyloBench
     */
    public <T> ZyloBench add(@NotNull Advanced<T> adv) {
        tasks.add(new Task<>(adv));
        return this;
    }

    /**
     * Sets the time unit to use
     *
     * @param timeUnit The time unit
     * @return The ZyloBench
     */
    public ZyloBench timeUnit(@NotNull TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
        return this;
    }

    /**
     * Sets the accuracy to use.
     * Like 2 = 0.00, 3 = 0.000, etc
     *
     * @param accuracy The accuracy
     * @return The ZyloBench
     */
    public ZyloBench accuracy(@Range(from = 0, to = Integer.MAX_VALUE) int accuracy) {
        this.accuracy = accuracy;
        return this;
    }

    /**
     * Sets the calculate type to use
     *
     * @param type The calculate type
     * @return The ZyloBench
     */
    public ZyloBench calculateBasedOn(@NotNull CalculateType type) {
        this.calculateType = type;
        return this;
    }

    /**
     * Runs the ZyloBench
     */
    public void go() {
        if (tasks.isEmpty()) {
            logger.info("\u001B[36m(• ᴖ •｡ ) ZyloBench | No Tasks Found (• ᴖ •｡ )\u001B[0m");
            return;
        }
        List<Result> results = new ArrayList<>();
        long maxTime = 0;

        logger.info("\u001B[36m⪩. .⪨ ZyloBench | Running Benchmarks ⪩. .⪨\u001B[0m");
        BasicUtils.printBreak();
        logger.info("");
        for (Task<?> t : tasks) {
            long[] times = Benchmark.runBenchmark(t, warm, runs);
            logger.info(String.format("\u001B[36m=^･ω･^= ZyloBench | %s finished =^･ω･^=\u001B[0m", t.name()));
            Result result = Results.calculate(t.name(), times);
            results.add(result);
            maxTime = Math.max(maxTime, result.max());
        }
        logger.info("");
        BasicUtils.printBreak();
        logger.info("");
        logger.info("\u001B[36m^•⩊•^ ZyloBench | Results ^•⩊•^\u001B[0m");
        BasicUtils.printBreak();
        logger.info("");
        for (Result result : results) {
            Results.print(result, timeUnit, maxTime, accuracy);
        }

        Summary.summary(results, calculateType);
    }
}