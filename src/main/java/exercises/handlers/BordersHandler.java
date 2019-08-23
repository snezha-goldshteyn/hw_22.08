package exercises.handlers;

import exercises.util.RandomUtil;
import exercises.annotations.Borders;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class BordersHandler extends Handler {

    @SneakyThrows
    @Override
    public double handle(Field field, double value) {
        Borders borders = field.getAnnotation(Borders.class);
        if (borders != null) {
            int min = borders.greaterThan();
            int max = borders.lessThan();
            return RandomUtil.getRandomValue(min, max);
        }
        else {
            return RandomUtil.getRandomValue(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
    }
}
