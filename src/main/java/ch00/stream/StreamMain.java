package ch00.stream;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMain {

    //테스트코드 별도작성
    public static void main(String[] args) {

        List<Dish> menu = new ArrayList<>();
        menu.add(new Dish("pork", false, 800, DishType.MEAT));
        menu.add(new Dish("beef", false, 700, DishType.MEAT));
        menu.add(new Dish("chiken", false, 400, DishType.MEAT));
        menu.add(new Dish("french fries", true, 530, DishType.OTHER));
        menu.add(new Dish("rice", true, 350, DishType.OTHER));
        menu.add(new Dish("season fruit", true, 120, DishType.OTHER));
        menu.add(new Dish("pizza", true, 550, DishType.OTHER));
        menu.add(new Dish("prawns", false, 300, DishType.FISH));
        menu.add(new Dish("salmon", false, 450, DishType.FISH));

        List<String> collect = menu.stream()
                                   .map(Dish::getName)
                                   .collect(Collectors.toList());

        for (String s : collect) {
            System.out.println("s = " + s);
        }


        menu.stream()
            .map(Dish::getDishType)
            .forEach(System.out::println);

        List<Integer> collect1 = menu.stream()
                                     .filter(dish -> dish.getCalories() > 300 && dish.getDishType()
                                                                                     .equals(DishType.MEAT))
                                     .map(Dish::getCalories)
                                     .limit(3)
                                     .collect(Collectors.toList());
        for (Integer integer : collect1) {
            System.out.println("integer = " + integer);
        }


    }

}
