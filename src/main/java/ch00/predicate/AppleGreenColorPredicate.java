package ch00.predicate;

import ch00.Apple;
import ch00.Color;

public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getColor()
                    .equals(Color.GREEN);
    }
}
