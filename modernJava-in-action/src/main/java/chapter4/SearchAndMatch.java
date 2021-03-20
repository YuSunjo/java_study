package chapter4;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SearchAndMatch {
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

        if(menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("menu is ");
        }

        menu.stream()
                .allMatch(dish -> dish.getCalories() < 1000);

        menu.stream().noneMatch(dish -> dish.getCalories() >= 1000);

        Optional<Dish> any = menu.stream().filter(Dish::isVegetarian).findAny();
        // Optional일 수도 있다. -> 밑에 코드처럼 바꿔야한다.

        menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(dish -> System.out.println("dish.getName() = " + dish.getName()));




    }
}
