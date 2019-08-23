package exercises;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Examinator examinator = new Examinator();
        List<Exercise> exercises = examinator.getExercises(10);
        for (Exercise exercise : exercises) {
            System.out.println(exercise);
        }

    }
}
