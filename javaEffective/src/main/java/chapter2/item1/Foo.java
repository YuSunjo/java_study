package chapter2.item1;
//생성자 대신 정적 팩터리 메서드를 고려하라

public class Foo {

    String name;

    String address;

    private static final Foo GOODNIGHT = new Foo();

    public Foo() {

    }

    public Foo(String name) {
        this.name = name;
    }

    //이름을 가질 수 있다.
    public static Foo withName(String name) {
        return new Foo(name);
    }

    public static Foo withAddress(String address) {
        Foo foo = new Foo();
        foo.address = address;
        return foo;
    }

    //새로운 객체를 만들 필요가 없다.
    public static Foo getGoodnight() {
        return GOODNIGHT;
    }

    //리턴 타입의 하위 타입 인스턴스를 만들 수 있다.
    public static Foo getBarFoo(boolean flag) {
        return flag ? new Foo() : new BarFoo();
    }

    //리턴 객체의 클래스가 public static 팩토리 메서드를 작성할 시점에 반드시 존재하지 않아도 된다.

}
