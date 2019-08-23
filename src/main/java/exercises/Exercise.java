package exercises;

import exercises.annotations.Borders;
import exercises.annotations.Rounding;
import exercises.annotations.NecessaryOperations;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NecessaryOperations(operations = {Operation.SUM, Operation.MULTIPLY})
public class Exercise {
    @Rounding(0)
    @Borders(greaterThan = 9, lessThan = 10)
    private double a;
    @Borders(greaterThan = 9, lessThan = 10)
    @Rounding
    private double b;
    private double answer;
    private Operation operation;

    @Override
    public String toString() {
        return String.format("%.2f %s %.2f = %.2f", a, operation.getOp(), b, answer);
    }

    public static class NecessaryOperationsHandler {
    }
}

