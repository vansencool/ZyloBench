package net.vansen.zylobench.tasks;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Advanced task class, used to create a task with an initial state, update function and runnable
 *
 * @param <T> The type of the state
 */
public class Advanced<T> {
    private final String name;
    private final Supplier<T> initial;
    private final Function<T, T> update;
    private final Consumer<T> execute;

    private Advanced(@NotNull String name, @NotNull Supplier<T> initial, @NotNull Function<T, T> update, @NotNull Consumer<T> execute) {
        this.name = name;
        this.initial = initial;
        this.update = update;
        this.execute = execute;
    }

    /**
     * The name of the task
     *
     * @return The name
     */
    public String name() {
        return name;
    }

    /**
     * The initial state
     *
     * @return The initial state
     */
    public Supplier<T> initial() {
        return initial;
    }

    /**
     * The update function
     *
     * @return The update function
     */
    public Function<T, T> update() {
        return update;
    }

    /**
     * The runnable to execute
     *
     * @return The runnable
     */
    public Consumer<T> execute() {
        return execute;
    }

    /**
     * Creates a new advanced task
     *
     * @param name    The name of the task
     * @param initial The initial state
     * @param update  The update function
     * @param execute The runnable to execute
     * @param <T>     The type of the state
     * @return The advanced task
     */
    public static <T> Advanced<T> as(@NotNull String name, @NotNull Supplier<T> initial, @NotNull Function<T, T> update, @NotNull Consumer<T> execute) {
        return new Advanced<>(name, initial, update, execute);
    }
}