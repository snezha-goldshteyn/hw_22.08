package exercises.handlers;

import java.lang.reflect.Field;

public abstract class Handler {
    public abstract double handle(Field field, double value);
}
