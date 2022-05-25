package chapter2.item6;

import java.util.regex.Pattern;

public class RomanNumerals {

    /**
     * 이처럼 값비싼 객체를 재사용해서 성능의 이점을 살려야한다.
     * 하지만 사용하지 않는다면 쓸데없이 초기화 한것이지만
     * 메서드가 처음 호출될 때 플드를 초기화 하는 지연초기화(lazy initialization)이 있지만 사용하지 않는 것이 좋음
     */
    private static final Pattern ROMAN = Pattern.compile(
            "\\."
    );

    static boolean isRomanNumeral(String s) {
        return ROMAN.matcher(s).matches();
    }

}
