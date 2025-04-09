package net.vansen.zylobench.tasks;

import org.jetbrains.annotations.NotNull;

/**
 * A normal task
 * <p>
 * Represents a task that only has a name and a runnable.
 */
public class Normal {

    private final String name;
    private final Runnable execute;

    private Normal(@NotNull String name, @NotNull Runnable execute) {
        this.name = name;
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
     * The runnable to execute
     *
     * @return The runnable
     */
    public Runnable execute() {
        return execute;
    }

    /**
     * Create a normal task with the given name and runnable
     *
     * @param name    The name
     * @param execute The runnable
     * @return The task
     */
    public static Normal as(@NotNull String name, @NotNull Runnable execute) {
        return new Normal(name, execute);
    }
}
