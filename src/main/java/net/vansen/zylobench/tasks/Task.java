package net.vansen.zylobench.tasks;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Represents a task, either a normal task or an advanced task
 *
 * @param <T> The type of advanced task
 */
public class Task<T> {
    private T state;
    private final Function<T, T> change;
    private final Consumer<T> task;
    private final String name;

    /**
     * Makes a task from an advanced task
     *
     * @param adv The advanced task
     */
    public Task(@NotNull Advanced<T> adv) {
        this.state = adv.initial().get();
        this.change = adv.update();
        this.task = adv.execute();
        this.name = adv.name();
    }

    /**
     * Makes a task from a normal task
     *
     * @param norm The normal task
     */
    public Task(@NotNull Normal norm) {
        this.name = norm.name();
        this.task = null;
        this.change = null;
        this.state = null;
        this.normRun = norm.execute();
    }

    private Runnable normRun = () -> {
    };

    /**
     * Runs the task provided
     */
    @SuppressWarnings("all")
    public long run() {
        if (task != null) {
            long start = System.nanoTime();
            task.accept(state);
            long time = System.nanoTime() - start;
            state = change.apply(state);
            return time;
        } else {
            long start = System.nanoTime();
            normRun.run();
            return System.nanoTime() - start;
        }
    }

    /**
     * The name of the task
     *
     * @return The name
     */
    public String name() {
        return name;
    }
}