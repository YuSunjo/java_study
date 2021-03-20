package chapter4;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

public class Reducing {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        //초기값
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println("sum = " + sum);
        numbers.stream().reduce(Integer::max);


    }
}
