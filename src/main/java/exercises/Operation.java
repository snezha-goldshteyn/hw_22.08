package exercises;

import lombok.Getter;

import java.util.function.BinaryOperator;

@Getter
public enum Operation {
    SUM("+", (a,b) -> a+b), SUBTRACTION("-", (a,b) -> a-b), MULTIPLY("*", (a,b) -> a*b);

    private String op;
    private BinaryOperator<Double> action;

    Operation(String op, BinaryOperator<Double> action) {
        this.op = op;
        this.action = action;
    }

    public static double getOperationResult (double a, double b, Operation operation) {
        return operation.action.apply(a,b);
    }
}
