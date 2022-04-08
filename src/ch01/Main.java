package ch01;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        ArrayList<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple(100, "green"));
        inventory.add(new Apple(150, "red"));
        inventory.add(new Apple(160, "yellow"));
        inventory.add(new Apple(170, "green"));
        inventory.add(new Apple(180, "red"));
        inventory.add(new Apple(190, "green"));
        inventory.add(new Apple(90, "green"));

        List<Apple> appleList = filterApple(inventory, (Apple a) -> a.getWeight() > 150 && a.getColor()
                                                                                            .equals("green"));
        List<Apple> list = inventory.parallelStream()
                                    .filter((apple -> apple.getWeight() > 150))
                                    .collect(Collectors.toList());

        System.out.println(appleList);
        System.out.println(list);
    }

    public static List<Apple> filterApple(List<Apple> list, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : list) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

}

class Apple {
    int weight;
    String color;

    public Apple(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "java8.Apple{" +
                "weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }
}

