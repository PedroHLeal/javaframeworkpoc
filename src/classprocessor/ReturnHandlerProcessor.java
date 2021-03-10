package classprocessor;

import annotations.Api;
import annotations.Provider;
import dto.ReflectedDto;
import handlers.ReturnHandler;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class ReturnHandlerProcessor extends ClassProcessor{
    public ReturnHandlerProcessor(Map<String, ReflectedDto> map) {
        super(map);
    }

    @Override
    public void processClass(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        if (clazz.isAnnotationPresent(Provider.class)) {
            if (ReturnHandler.class.isAssignableFrom(clazz)) {
                map.put(
                    ((ParameterizedType)clazz.getGenericInterfaces()[0]).getActualTypeArguments()[0].getTypeName(),
                    new ReflectedDto(clazz.newInstance(), null)
                );
            }
        }
    }
}
