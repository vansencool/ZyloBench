package net.vansen.zylobench.tasks;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

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

    public static <T> Advanced<T> as(@NotNull String name, @NotNull Supplier<T> initial, @NotNull Function<T, T> update, @NotNull Consumer<T> execute) {
        return new Advanced<>(name, initial, update, execute);
    }
}