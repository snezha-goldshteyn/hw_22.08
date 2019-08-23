package exercises.service;

import exercises.Operation;

public interface ExaminatorService {
    Operation getOperations();
    double getOperand (String name);
    double getAnswer(double a, double b, Operation op);
}
