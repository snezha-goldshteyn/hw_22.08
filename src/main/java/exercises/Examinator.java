package exercises;

import exercises.service.ExaminatorService;
import exercises.service.ExaminatorServiceImpl;

import java.util.*;

public class Examinator {
    ExaminatorService service = new ExaminatorServiceImpl();

    public List<Exercise> getExercises(int amount) {
        List<Exercise> exercises = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
             double a = service.getOperand("a");
             double b = service.getOperand("b");
             Operation op = service.getOperations();
             double answer = service.getAnswer(a, b, op);
             exercises.add(Exercise.builder().a(a).b(b).operation(op).answer(answer).build());
        }
        return exercises;
    }


}
