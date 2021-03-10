package classprocessor;

import dto.ReflectedDto;

import java.util.Map;

public abstract class ClassProcessor {
    Map<String, ReflectedDto> map;

    public ClassProcessor(Map<String, ReflectedDto> map) {
        this.map = map;
    }

    public abstract void processClass(Class<?> clazz) throws IllegalAccessException, InstantiationException;
}
