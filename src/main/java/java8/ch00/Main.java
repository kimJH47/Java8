package java8.ch00;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple(Color.RED, 100));
        inventory.add(new Apple(Color.RED, 110));
        inventory.add(new Apple(Color.GREEN, 150));
        inventory.add(new Apple(Color.RED, 140));
        inventory.add(new Apple(Color.RED, 200));
        inventory.add(new Apple(Color.GREEN, 130));
        inventory.add(new Apple(Color.RED, 90));
        inventory.add(new Apple(Color.GREEN, 95));

    }

    public static List<Apple> filterGreenApple(List<Apple> inventory) {
        ArrayList<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(Color.GREEN)) {
                result.add(apple);
            }
        }
        return result;
    }
    public static List<Apple> filterHeavyAndRedApple(List<Apple> inventory) {
        ArrayList<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 100 && apple.getColor().equals(Color.RED)){
                result.add(apple);
            }
        }
        return result;
    }
    public static List<Apple> filterHeavyApple(List<Apple> inventory) {
        ArrayList<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 100) {
                result.add(apple);
            }
        }
        return result;
    }
}
