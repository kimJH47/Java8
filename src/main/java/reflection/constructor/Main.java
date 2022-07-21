package reflection.constructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        //Class.getDeclaredConstructors() : public 여부에 상관없이 모든 생성자 반환
        //Class.getConstructors() : public 생성자만 반환
        // 특정 생성자를 찾을 때 가져오는 매개변수의 타입을 안다면 매개변수의 타입을 나타내는 타입 목록을 인자로 전달 할 수있음
        //알맞은 생성자가 없으면 NoSuchMethodException 발생
        //생성자가 없을시 기본 생성자가 생성되고 반환값 역시 단일요소 배열로 반환

        printConstructors(Address.class);
        Person person1 = createInstanceWithArgument(Person.class);
        System.out.println(person1);
        Person kim =  createInstanceWithArgument(Person.class, "kim", 20);
        System.out.println(kim);
        Address address =  createInstanceWithArgument(Address.class, "First Street", 10);
        Person person2 =  createInstanceWithArgument(Person.class, address,"kim", 30);
        System.out.println(person2);



    }

    public static void printConstructors(Class<?> clazz) {
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        System.out.println(String.format("class %s has %d declared constructors", clazz.getSimpleName(), declaredConstructors.length));

        for (Constructor<?> declaredConstructor : declaredConstructors) {
            Class<?>[] parameterTypes = declaredConstructor.getParameterTypes();
            List<String> parameterTypeNames = Arrays.stream(parameterTypes)
                                                    .map(type -> type.getSimpleName())
                                                    .collect(Collectors.toList());
            System.out.println(parameterTypeNames);


        }
    }

    public static<T> T createInstanceWithArgument(Class<T> clazz, Object... args)
            throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            if (declaredConstructor.getParameterTypes().length == args.length) {
                //Exception은 일단 무시
                return (T)declaredConstructor.newInstance(args);
            }
        }
        System.out.println("An appropriate constructor was not found");
        return null;
    }


    static class Person {

        private final Address address;
        private final String name;
        private final int age;

        public Person() {
            this.name = "anonymous";
            this.age = 0;
            this.address = null;
        }

        public Person(Address adress, String name, int age) {
            this.address = adress;
            this.name = name;
            this.age = age;
        }

        public Person(String name) {
            this.name = name;
            this.address = null;
            this.age = 0;
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
            this.address = null;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "adress=" + address +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    static class Address {
        private String street;
        private int number;

        public Address(String street, int number) {
            this.street = street;
            this.number = number;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "street='" + street + '\'' +
                    ", number=" + number +
                    '}';
        }
    }
}


