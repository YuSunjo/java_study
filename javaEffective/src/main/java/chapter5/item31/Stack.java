package chapter5.item31;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;

public class Stack<E>  {

    private List<E> elements;
    private int size = 0;

    public Stack() {
        this.elements = new ArrayList<>();
    }

    public static void main(String[] args) {
        Stack<Number> stack = new Stack<>();
        Iterable<Integer> integers = Arrays.asList(1);
        stack.pushAll(integers);
    }

    public void push(E o) {
        elements.add(o);
        size++;
    }

    public <E> E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        E element = (E)elements.get(size);
        elements.remove(size--);
        return element;
    }

    public void pushAll(Iterable<? extends E> src) {
        for (E e : src) {
            push(e);
        }
    }

    public boolean isEmpty() {
        return elements.size() == 0;
    }

}
