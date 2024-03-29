package java8.ch00;

import java.util.Objects;

public class Apple {
    private Color color;
    private int weight;
    private double brix;

    public Apple(Color color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public Apple(Color color, int weight, double brix) {
        this.color = color;
        this.weight = weight;
        this.brix = brix;
    }

    public Apple(int weight) {
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getBrix() {
        return brix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apple apple = (Apple) o;
        return getWeight() == apple.getWeight() && getColor() == apple.getColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor(), getWeight());
    }
}
