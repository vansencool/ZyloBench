package net.vansen.zylobench.summary;

import net.vansen.zylobench.ZyloBench;
import net.vansen.zylobench.calculate.CalculateType;
import net.vansen.zylobench.result.Result;
import net.vansen.zylobench.utils.BasicUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class Summary {

    public static void summary(@NotNull List<Result> results, @NotNull CalculateType calculateType) {
        if (results.size() >= 2) {
            List<Result> sortedResults = results.stream()
                    .sorted(Comparator.comparingLong(Result::avg))
                    .toList();

            Result fastest = sortedResults.getFirst();

            ZyloBench.logger.info("");
            ZyloBench.logger.info("\u001B[36mBenchmark Summary\u001B[0m");
            BasicUtils.printBreak();

            ZyloBench.logger.info(String.format("\u001B[36m%s\u001B[0m", fastest.name()));
            for (int i = 1; i < sortedResults.size(); i++) {
                Result current = sortedResults.get(i);
                double speedup = calculateType.speedup(current, fastest);
                ZyloBench.logger.info(String.format("  \u001B[36m%s \u001B[34m%.2fX\u001B[36m\u001B[0m", current.name(), speedup));
            }
        }
    }
}
