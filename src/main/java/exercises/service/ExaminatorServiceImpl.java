package exercises.service;

import exercises.Exercise;
import exercises.annotations.NecessaryOperations;
import exercises.Operation;
import exercises.util.RandomUtil;
import exercises.handlers.Handler;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class ExaminatorServiceImpl implements ExaminatorService {
    Class<Exercise> eClass = Exercise.class;
    Map<Annotation, Handler> map;

    public ExaminatorServiceImpl() {
        map = Arrays.stream(eClass.getDeclaredFields()).flatMap(f -> Arrays.stream(f.getAnnotations()))
                .collect(toMap(a->a, this::getHandler, (a,b) -> a));
    }

    @SneakyThrows
    private Handler getHandler(Annotation a) {
        Reflections scanner = new Reflections("exercises.handlers");
        Set<Class<? extends Handler>> classes = scanner.getSubTypesOf(Handler.class);
        Class<? extends Handler> aClass = classes.stream().filter(hClass -> !Modifier.isAbstract(hClass.getModifiers()))
                .filter(hClass -> equalToName(a, hClass)).findAny().orElseThrow();
        return aClass.getDeclaredConstructor().newInstance();
    }

    @SneakyThrows
    private boolean equalToName(Annotation a, Class<? extends Handler> hClass) {
        String aName = (String) a.getClass().getDeclaredMethod("name").invoke(a);
        String hName = aName + "Handler";
        return hClass.getSimpleName().equals(hName);
    }

    public double getAnswer(double a, double b, Operation op) {
        return Operation.getOperationResult(a,b,op);
    }

    @SneakyThrows
    public double getOperand (String name) {
        Field field = eClass.getDeclaredField(name);
        field.setAccessible(true);
        double value = 0;
        Annotation[] annotations = field.getAnnotations();
        for (Annotation annotation : getHandlerAnnotations(annotations)) {
            if (annotation != null) {
                Handler handler = map.get(annotation);
                value = handler.handle(field, value);
            }
        }
        return value;
    }

    private List<Annotation> getHandlerAnnotations(Annotation[] annotations) {
        List<Annotation> handlerAnnotations = new ArrayList<>(map.keySet());
        return Stream.of(annotations).filter(handlerAnnotations::contains)
                .sorted(this::compareLevels).collect(Collectors.toList());
    }

    @SneakyThrows
    private int compareLevels(Annotation a1, Annotation a2) {
        int levelA1 = (int) a1.getClass().getDeclaredMethod("level").invoke(a1);
        int levelA2 = (int) a2.getClass().getDeclaredMethod("level").invoke(a2);
        return levelA1 - levelA2;
    }

    @SneakyThrows
    public Operation getOperations() {
        Operation[] operations;
        NecessaryOperations annotation = eClass.getAnnotation(NecessaryOperations.class);
        if (annotation != null) {
            operations = annotation.operations();
        } else {
              operations = Operation.values();
        }
        int i = (int) RandomUtil.getRandomValue(0, operations.length);
        return operations[i];
    }
}
