package chapter2.ApplePredicate;

import chapter2.Apple;

public interface ApplePredicate {

    boolean test(Apple apple);

    String accept(Apple apple);

}
