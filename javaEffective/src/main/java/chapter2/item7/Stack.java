package chapter2.item7;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack {
    private static final int DEFAULT_INITAL_CAPACITY = 16;

    private Object[] elements;
    private int size = 0;

    public Stack() {
        elements = new Object[DEFAULT_INITAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        Object element = elements[size++];
    }

    /**
     * stack 이 커졌다가 줄어들 떄 스택에서 꺼내진 객체들을 가비지 컬렉터가 회수하지 않음
     */
//    public Object pop() {
//        if (size == 0) {
//            throw new EmptyStackException();
//        }
//        return elements[--size];
//    }

    // 해결방법
    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }

        Object result = elements[--size];
        elements[size] = null;
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
