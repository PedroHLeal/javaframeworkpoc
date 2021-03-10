package classprocessor;

import annotations.Api;
import annotations.EndPoint;
import dto.ReflectedDto;

import java.lang.reflect.Method;
import java.util.Map;

public class ApiProcessor extends ClassProcessor{

    public ApiProcessor(Map<String, ReflectedDto> map) {
        super(map);
    }

    @Override
    public void processClass(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        if (clazz.isAnnotationPresent(EndPoint.class)) {
            Object instance = clazz.newInstance();
            for (Method m : clazz.getMethods()) {
                if (m.isAnnotationPresent(Api.class)) {
                    Api a = m.getAnnotation(Api.class);
                    map.put(a.path(), new ReflectedDto(instance, m));
                }
            }
        }
    }
}
