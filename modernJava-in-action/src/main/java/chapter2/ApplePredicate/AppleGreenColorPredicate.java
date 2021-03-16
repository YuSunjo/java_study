package chapter2.ApplePredicate;

import chapter2.Apple;
import chapter2.변화하는_요구사항_대응.변화하는_요구사항_대응;

public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return 변화하는_요구사항_대응.Color.GREEN.equals(apple.getColor());
    }

    @Override
    public String accept(Apple apple) {
        return apple.getColor();
    }
}
