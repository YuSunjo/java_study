package chapter4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Type.FISH),
                new Dish("pork", false, 800, Type.FISH),
                new Dish("pork", false, 800, Type.FISH),
                new Dish("pork", false, 800, Type.FISH),
                new Dish("pork", false, 800, Type.FISH),
                new Dish("pork", false, 800, Type.FISH),
                new Dish("pork", false, 800, Type.FISH)
        );

        menu.stream().filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());

        // 문제 1
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> result = integers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println("result = " + result);

        // 문제 2
        List<Integer> a1 = Arrays.asList(1, 2, 3);
        List<Integer> a2 = Arrays.asList(3, 4);

        List<int[]> integer2 = a1.stream().flatMap(i -> a2.stream().map(j -> new int[]{i, j})).collect(Collectors.toList());
        System.out.println("integer2 = " + integer2);

        //문제 3

    }
}
