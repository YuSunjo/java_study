package chapter2.ApplePredicate;

import chapter2.Apple;

public class AppleHeavyWeightPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return (apple.getWeight() > 150);
    }

    @Override
    public String accept(Apple apple) {
        return String.format("An apple of (%s)g", apple.getWeight());
    }
}
