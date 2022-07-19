package reflection.ex03;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Exercise {

    /**
     * Returns all the interfaces that the current input class implements.
     * Note: If the input is an interface itself, the method returns all the interfaces the
     * input interface extends.
     */
    public static Set<Class<?>> findAllImplementedInterfaces(Class<?> input) {
        Set<Class<?>> allImplementedInterfaces = new HashSet<>();

        /** Complete this code **/
        Class<?>[] inputInterfaces = input.getInterfaces();

        for (Class<?> currentInterface : inputInterfaces) {
            System.out.println(currentInterface.getSimpleName());
            allImplementedInterfaces.add(currentInterface);
            /** Complete this code **/
            Set<Class<?>> allImplementedInterfaces1 = findAllImplementedInterfaces(currentInterface);
            allImplementedInterfaces.addAll(allImplementedInterfaces1);
        }
        return allImplementedInterfaces;
    }

    public static void main(String[] args) {

        Set<Class<?>> allImplementedInterfaces = Exercise.findAllImplementedInterfaces(ArrayList.class);
        System.out.println("="+allImplementedInterfaces);
    }
}
