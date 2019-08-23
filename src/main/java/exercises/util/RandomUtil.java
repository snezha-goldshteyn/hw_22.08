package exercises.util;

import java.util.Random;

public class RandomUtil {
    private static Random random = new Random();

    public static double getRandomValue (int min, int max) {
        return random.doubles(min, max).findAny().getAsDouble();
    }
}
