package utill;

import leetcode.dataStructure.DisjointSet;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Runner {
    public void run() throws Exception {
        // Just instantiate an object here;
        DisjointSet targetClass = new DisjointSet();

        doTheTrick(targetClass);
    }

    private void doTheTrick(Object targetObject) throws Exception {
        Method publicMethod = getRunnablePublicMethod(targetObject); // method to run

        long startTime = System.currentTimeMillis();
        System.out.println(publicMethod.invoke(targetObject)); // run the method
        System.out.println("Took " + (System.currentTimeMillis() - startTime) + " milli seconds to run");
    }


    private Method getRunnablePublicMethod(Object object) throws Exception {

        Method[] allMethods = object.getClass().getDeclaredMethods();
        List<Method> publicMethods = Arrays.stream(allMethods)
                .filter(method -> Modifier.isPublic(method.getModifiers())) // public but not constructor
                .collect(Collectors.toList());

        if (publicMethods.size() > 1) {
            throw new Exception("multiple public method in class: " + object.getClass().getName());
        } else if (publicMethods.size() == 0) {
            throw new Exception("No public method found in class: " + object.getClass().getName());
        }

        return publicMethods.get(0);
    }
}
