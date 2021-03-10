
import classprocessor.FrameworkContext;
import dto.ReflectedDto;
import handlers.Response;
import handlers.ReturnHandler;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.List;

public class Main {
    private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        if (!directory.exists()) {
            return classes;
        }

        File[] files = directory.listFiles();
        for (File file : files) {
            String pack = packageName.equals("") ? packageName : packageName + ".";
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, pack + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(pack + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    public void loadClasses() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Map<String, ReflectedDto> apis = new HashMap<>();
        List<Class<?>> classes = findClasses(new File("."), "");
        FrameworkContext fc = FrameworkContext.getInstance();

        for (Class<?> clazz : classes) {
            fc.processClass(clazz);
        };
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        FrameworkContext fc = FrameworkContext.getInstance();

        try {
            main.loadClasses();
            Map<String, ReflectedDto> apis = fc.apis;
            Map<String, ReflectedDto> returnHandlers = fc.returnHandlers;
            while (true) {
                System.out.println("Framework loaded - Enter apis to be executed");
                String api = sc.nextLine();
                if (apis.containsKey(api)) {
                    Object instance = apis.get(api).instance;
                    Method method = apis.get(api).m;
                    try {
                        Object o = method.invoke(instance);
                        System.out.println((String)o);
                    } catch (InvocationTargetException ite) {

//                        Class<?> gt = t.getClass();
//                        String typeName = t.getTypeName();
                        ReturnHandler rh = (ReturnHandler) returnHandlers.get(ite.getTargetException().getClass().getName()).instance;
                        Response r = rh.transform(ite.getTargetException());
                        System.out.println(r.message);
                    }

                } else {
                    System.out.println("Api not found");
                }
            }
        } catch (IllegalAccessException | ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
