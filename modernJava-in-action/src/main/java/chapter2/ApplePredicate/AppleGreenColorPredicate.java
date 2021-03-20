package chapter2.ApplePredicate;

import chapter2.Apple;
import chapter2.chapter.lambdaA;

public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return lambdaA.Color.GREEN.equals(apple.getColor());
    }

    @Override
    public String accept(Apple apple) {
        return apple.getColor();
    }
}
