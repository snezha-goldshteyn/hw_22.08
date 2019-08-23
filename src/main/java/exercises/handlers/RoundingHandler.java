package exercises.handlers;

import exercises.annotations.Rounding;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class RoundingHandler extends Handler {
    @SneakyThrows
    @Override
    public double handle(Field field, double value) {
        Rounding rounding = field.getAnnotation(Rounding.class);
        if (rounding != null) {
            double pow = Math.pow(10, rounding.value());
            value = Math.round(value*pow)/pow;
        }
        return value;
    }
}
