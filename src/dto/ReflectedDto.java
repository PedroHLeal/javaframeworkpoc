package dto;

import java.lang.reflect.Method;

public class ReflectedDto {
    public Object instance;
    public Method m;

    public ReflectedDto(Object instance, Method m) {
        this.instance = instance;
        this.m = m;
    }
}
