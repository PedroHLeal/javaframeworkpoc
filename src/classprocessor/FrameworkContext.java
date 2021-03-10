package classprocessor;

import dto.ReflectedDto;
import handlers.ReturnHandler;

import java.util.*;

public class FrameworkContext {
    public Map<String, ReflectedDto> apis = new HashMap<>();
    public Map<String, ReflectedDto> returnHandlers = new HashMap<>();
    List<ClassProcessor> processors = new ArrayList<>();

    public static FrameworkContext instance = new FrameworkContext();

    public FrameworkContext() {
        processors.add(new ApiProcessor(apis));
        processors.add(new ReturnHandlerProcessor(returnHandlers));
    }

    public void processClass(Class<?> clazz) throws InstantiationException, IllegalAccessException {
        for (ClassProcessor processor : processors) {
            processor.processClass(clazz);
        }
    }

    public static FrameworkContext getInstance() {
        return instance;
    }
}
