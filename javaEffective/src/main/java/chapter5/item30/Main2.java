package chapter5.item30;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Main2 {

    public static <E extends Comparable<E>> Optional<E> max(Collection<E> collection) {
        if (collection.isEmpty())
            return Optional.empty();

        E result = null;

        for (E e : collection) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }

        return Optional.of(result);
    }

    public static void main(String[] args) {
        List<Integer> list = List.of(2, 14, 5, 11, 3);
        int result = max(list).orElseGet(null);
        System.out.println("result = " + result);
    }

}
