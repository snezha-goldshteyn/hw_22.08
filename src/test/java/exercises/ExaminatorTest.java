package exercises;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ExaminatorTest {
    @Test
    public void getExercises() {
        Examinator examinator = new Examinator();
        List<Exercise> exercises = examinator.getExercises(10);
        assertEquals(10,exercises.size());
    }

}