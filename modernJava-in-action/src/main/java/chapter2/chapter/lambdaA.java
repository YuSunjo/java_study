package chapter2.chapter;

import chapter2.Apple;
import chapter2.ApplePredicate.ApplePredicate;

import java.util.ArrayList;
import java.util.List;

public class lambdaA {

    public enum Color { RED, GREEN }

//    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
//        ArrayList<Apple> result = new ArrayList<>();
//        for (Apple apple : inventory) {
//            if (apple.getColor().equals(color)) {
//                result.add(apple);
//            }
//        }
//        return result;
//    }

    public static List<Apple> filterApplesByColor(List<Apple> inventory, ApplePredicate p) {
        ArrayList<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    // 필터 사용하기 위해 준비
    public static <T> List<T> filter(List<T> list, Predicate<T> p ) {
        List<T> result = new ArrayList<>();
        for (T e: list) {
            if(p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    // 퀴즈 2-1
    public static List<Apple> prettyPrintApple(List<Apple> inventory, ApplePredicate predicate) {
        ArrayList<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            predicate.accept(apple);
        }
        return result;
    }

}
