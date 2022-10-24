package java8.ch00.predicate;

import java8.ch00.Apple;

public class AppleHeavyWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 100;
    }
}
