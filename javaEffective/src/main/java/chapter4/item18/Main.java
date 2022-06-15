package chapter4.item18;

import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CustomHashSetByInheritance<Integer> customHashSetByInheritance = new CustomHashSetByInheritance<>();
        List<Integer> numbers = List.of(1, 2, 3, 4);
        customHashSetByInheritance.addAll(numbers);
        customHashSetByInheritance.add(5);

        // 5가 나와야할것 같은데 9가 나옴
        System.out.println("customHashSetByInheritance = " + customHashSetByInheritance.getAddCount());

        CustomHashSetByComposition<Integer> customHashSetByComposition = new CustomHashSetByComposition<>(new HashSet<>());
        customHashSetByComposition.addAll(numbers);
        customHashSetByComposition.add(5);

        System.out.println("customHashSetByComposition = " + customHashSetByComposition.getAddCount());
    }
}
