package chapter2.item3;

public class Elvis {

    private static final Elvis Instance = new Elvis();

    // private 성성자를 통해서 싱글턴임을 보장해야한다.

//    public static final Elvis Instance = new Elvis();
//    private Elvis() {
//
//    }

    /**
     * 정적 팩토리 메서드
     *  getInstance 는 항상 같은 객체의 참조를 반환하므로 싱글턴임을 보장할 수 있다.
     *
     *
     * 싱글턴이란?
     *
     */

    public static Elvis getInstance() {
        return Instance;
    }

}
