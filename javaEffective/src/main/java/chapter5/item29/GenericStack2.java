package chapter5.item29;

import java.util.EmptyStackException;

public class GenericStack2<E> {

    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public GenericStack2() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY]; // ₩러 발생
    }

    public void push(E e) {
        elements[size++] = e;
    }

    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }

        E result = (E) elements[--size];
        elements[size] = null;
        return result;
    }

}
