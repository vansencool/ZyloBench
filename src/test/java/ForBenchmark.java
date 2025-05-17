import net.vansen.zylobench.ZyloBench;
import net.vansen.zylobench.tasks.Normal;

@SuppressWarnings("all")
public class ForBenchmark {

    public static int MANY = 3500;

    public static void main(String[] args) {
        ZyloBench.make()
                .warm(10000)
                .runs(100000)
                .accuracy(2)
                .add(Normal.as("For", () -> {
                    for (int ignored = 0; ignored < MANY; ignored++) {
                    }
                }))
                .add(Normal.as("While", () -> {
                    int ignored = 0;
                    while (ignored < MANY) {
                        ignored++;
                    }
                }))
                .add(Normal.as("Stream", () -> java.util.stream.IntStream.range(0, MANY).forEach(ignored -> {

                })))
                .add(Normal.as("Parallel Stream", () -> java.util.stream.IntStream.range(0, MANY).parallel().forEach(ignored -> {

                })))
                .add(Normal.as("For Each", () -> {
                    for (int ignored : new int[MANY]) {
                    }
                }))
                .go();
    }
}
