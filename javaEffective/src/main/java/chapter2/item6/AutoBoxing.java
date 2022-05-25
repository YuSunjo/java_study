package chapter2.item6;

public class AutoBoxing {

    /**
     * sum 변수를 long 이 아닌 Long 으로 선언하여 느리다.
     * 계속 불필요한 Long 인스턴스가 생성되고 있다.
     * 박싱된 기본 타입보다는 기본타입을 사용하고 의도치 않은 오토박싱이 숨어들지 않도록 주의
     */
//    public static long sum() {
//        Long sum = 0L;
//        for (int i = 0; i < Integer.MAX_VALUE; i++) {
//            sum += i;
//        }
//        return sum;
//    }

    public static long sum() {
        long sum = 0L;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }


}
