package chapter2.변화하는_요구사항_대응;

import chapter2.Apple;
import chapter2.ApplePredicate.ApplePredicate;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static chapter2.변화하는_요구사항_대응.변화하는_요구사항_대응.filter;
import static chapter2.변화하는_요구사항_대응.변화하는_요구사항_대응.filterApplesByColor;

public class 익명클래스 {
    public static void main(String[] args) {

        List<Apple> inventory = Arrays.asList(new Apple("green", 80),new Apple("green", 80),new Apple("green", 80));

        filterApplesByColor(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return 변화하는_요구사항_대응.Color.RED.equals(apple.getColor());
            }

            @Override
            public String accept(Apple apple) {
                return String.format("무게는 (%s)입니다.", apple.getWeight());
            }
        });

        //이런식으로 줄일 수 있음
//        filterApplesByColor(inventory, (Apple apple) -> 변화하는_요구사항_대응.Color.RED.equals(apple.getColor()));

        filter(inventory, (Apple apple) -> 변화하는_요구사항_대응.Color.RED.equals(apple.getColor()));

        // 이런식으로 줄일 수 있음
//        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
    }


}
