package net.vansen.zylobench.tasks.builder;

import net.vansen.zylobench.tasks.Normal;
import org.jetbrains.annotations.NotNull;

/**
 * Builder for normal tasks
 */
@SuppressWarnings("unused")
public class NormalBuilder {

    private String name;
    private Runnable execute;

    /**
     * Set the name of the task
     *
     * @param name The name
     * @return this builder
     */
    public NormalBuilder as(@NotNull String name) {
        this.name = name;
        return this;
    }

    /**
     * The runnable to execute
     *
     * @param execute The runnable
     * @return this builder
     */
    public NormalBuilder execute(@NotNull Runnable execute) {
        this.execute = execute;
        return this;
    }

    /**
     * The normal task that has been built
     *
     * @return The normal task
     */
    public Normal build() {
        return Normal.as(name, execute);
    }
}
