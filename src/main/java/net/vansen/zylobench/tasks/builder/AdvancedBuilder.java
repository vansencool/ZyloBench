package net.vansen.zylobench.tasks.builder;

import net.vansen.zylobench.tasks.Advanced;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A builder for advanced tasks
 *
 * @param <T> The type of data for the advanced task
 */
@SuppressWarnings("unused")
public class AdvancedBuilder<T> {
    private String name;
    private Supplier<T> initial;
    private Function<T, T> update;
    private Consumer<T> execute;

    /**
     * The name of the task
     *
     * @param name The name
     * @return this builder
     */
    public AdvancedBuilder<T> as(@NotNull String name) {
        this.name = name;
        return this;
    }

    /**
     * The initial state and update function for the task
     *
     * @param initial the initial data for the task
     * @param update  the function to update the data
     * @return this builder
     */
    public AdvancedBuilder<T> value(@NotNull Supplier<T> initial, @NotNull Function<T, T> update) {
        this.initial = initial;
        this.update = update;
        return this;
    }

    /**
     * The runnable to execute
     *
     * @param execute The runnable
     * @return this builder
     */
    public AdvancedBuilder<T> execute(Consumer<T> execute) {
        this.execute = execute;
        return this;
    }

    /**
     * The advanced task that has been built
     *
     * @return The advanced task
     */
    public Advanced<T> build() {
        return Advanced.as(name, initial, update, execute);
    }
}