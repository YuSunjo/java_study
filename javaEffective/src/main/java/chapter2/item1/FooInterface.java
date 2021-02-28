package chapter2.item1;

public interface FooInterface {

    //인터페이스에 정적 메서드를 가질 수 있다.
    public static Foo getFoo() {
        return new Foo();
    }

}
