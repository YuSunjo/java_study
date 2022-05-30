package chapter3.item11;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<PhoneNumber, String> m = new HashMap<>();
        m.put(new PhoneNumber(707, 867, 5309), "Jenny"); // 1

        System.out.println(m.get(new PhoneNumber(707, 867, 5309))); // 2
        // "Jenny" 가 나와야 할 것 같지만 null을 반환한다.
    }

}
