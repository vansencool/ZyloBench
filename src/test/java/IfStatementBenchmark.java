import net.vansen.zylobench.ZyloBench;
import net.vansen.zylobench.tasks.Normal;

/**
 * A benchmark for if statements
 * <p>
 * Really just as a example, don't take this seriously or anything
 */
@SuppressWarnings("all")
public class IfStatementBenchmark {

    public static void main(String[] args) {
        ZyloBench.make()
                .warm(100000)
                .runs(1000000)
                .accuracy(2)
                .add(Normal.as("If", () -> {
                    if (true) {
                    }
                }))
                .add(Normal.as("If And Else If", () -> {
                    if (false) {
                    } else if (true) {
                    }
                }))
                .add(Normal.as("If Null Equals Null", () -> {
                    if (null == null) {
                    }
                }))
                .add(Normal.as("If Boolean Variable", () -> {
                    boolean flag = true;
                    if (flag) {
                    }
                }))
                .add(Normal.as("If Less Than", () -> {
                    int a = 1;
                    int b = 2;
                    if (a < b) {
                    }
                }))
                .add(Normal.as("If Less Than Equals", () -> {
                    int a = 1;
                    int b = 2;
                    if (a <= b) {
                    }
                }))
                .add(Normal.as("If Greater Than", () -> {
                    int a = 1;
                    int b = 2;
                    if (a > b) {
                    }
                }))
                .add(Normal.as("If Greater Than Equals", () -> {
                    int a = 1;
                    int b = 2;
                    if (a >= b) {
                    }
                }))
                .add(Normal.as("String Equals", () -> {
                    if ("".equals("")) {
                    }
                }))
                .add(Normal.as("String Equals Ignore Case", () -> {
                    if ("".equalsIgnoreCase("")) {
                    }
                }))
                .add(Normal.as("String Contains", () -> {
                    if ("".contains("")) {
                    }
                }))
                .add(Normal.as("String StartsWith", () -> {
                    if ("".startsWith("")) {
                    }
                }))
                .add(Normal.as("String EndsWith", () -> {
                    if ("".endsWith("")) {
                    }
                }))
                .go();
    }
}
