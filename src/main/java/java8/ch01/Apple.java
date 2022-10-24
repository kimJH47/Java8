package java8.ch01;

public class Apple {

    private int weight;

    private Color color;

    public Apple(int weight, java8.ch00.Color green) {
        this.weight = weight;
    }

    public Apple(int weight, Color color) {

        this.weight = weight;
        this.color = color;

    }




    public Color getColor() {

        return color;

    }


    public int getWeight() {

        return weight;

    }


}