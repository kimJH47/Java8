package ch00.stream;

import java.util.Objects;

public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final DishType dishType;

    public Dish(String name, boolean vegetarian, int calories, DishType dishType) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.dishType = dishType;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public DishType getDishType() {
        return dishType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return getCalories() == dish.getCalories();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCalories());
    }
}
