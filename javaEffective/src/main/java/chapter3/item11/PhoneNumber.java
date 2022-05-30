package chapter3.item11;

import java.util.Objects;

public class PhoneNumber {

    private int a;
    private int b;
    private int c;

    public PhoneNumber(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return a == that.a && b == that.b && c == that.c;
    }

}
