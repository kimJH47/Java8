package java8.ch00.predicate;

import java8.ch00.Apple;
import java8.ch00.Color;

public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getColor()
                    .equals(Color.GREEN);
    }
}
