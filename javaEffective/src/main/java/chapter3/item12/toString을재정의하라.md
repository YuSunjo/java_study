### toString을 항상 재정의하라

**toString 이란?**
* toString() 은 Object 클래스의 메서드
* 모든 객체는 Object를 상속하기 때문에 toString()을 가지고 있음
```
public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
        }
```
* Object의 기본 toString() 은 클래서으 적합한 문자열을 반환하지 않음 => 클래스이름@16진수해쉬코드 형태

**언제 사용하는가?**
보통 객체를 표현하는 문자열
1. 콘솔로 객체 확인
   * System.out.println()
   * assert 구문 사용 시
   * 객체 문자열 연결
2. 디버깅
3. 로깅

**어떻게 재정의 해야하나?**
* 재정의할 때 가장 중요한 건 객체 스스로를 완벽히 설명하는 문자열이어야 한다는 것입니다.
* 객체가 가진 주요 정보를 모두 반환
```
/** 
 * 전화번호의 문자열 표현을 반환합니다.
 * 이 문자열은 XXX-YYYY-ZZZZ 형태의 11글자로 구성됩니다.
 * XXX는 지역코드, YYYY는 접두사, ZZZZ는 가입자 번호입니다.
 * 블라블라~
*/
@Override
public String toString() {
    return String.format("%03d-%04d-%04d", areaCode, prefix, lineNum);
}
```
* 다만 포맷을 정해준다면 앞으로의 유지보수에도 항상 이 포맷을 써야하기 때문에 신중히 포맷을 정할 필요가 있음

* 유틸리티 클래스는 toString()을 사용할 이유가 없고, Enum 은 아래 코드처럼 toString() 이미 정의되어 있으므로 toString()을 재정의하지 않아도 됩니다.
```
public abstract class Enum<E extends Enum<E>> implements Comparable<E>, Serializable {

    private final String name;

    public String toString() {
        return name;
    }
}
```

**toString()의 예시**
BigInteger 의 toString()
```
public String toString(int radix) {
    if (signum == 0)
        return "0";
    if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX)
        radix = 10;

    // If it's small enough, use smallToString.
    if (mag.length <= SCHOENHAGE_BASE_CONVERSION_THRESHOLD)
       return smallToString(radix);

    // Otherwise use recursive toString, which requires positive arguments.
    // The results will be concatenated into this StringBuilder
    StringBuilder sb = new StringBuilder();
    if (signum < 0) {
        toString(this.negate(), sb, radix, 0);
        sb.insert(0, '-');
    }
    else
        toString(this, sb, radix, 0);

    return sb.toString();
}
```

AbstractMap<K,V>의 toString() → HashMap의 상위 추상 클래스
```
public String toString() {
    Iterator<Entry<K,V>> i = entrySet().iterator();
    if (! i.hasNext())
        return "{}";

    StringBuilder sb = new StringBuilder();
    sb.append('{');
    for (;;) {
        Entry<K,V> e = i.next();
        K key = e.getKey();
        V value = e.getValue();
        sb.append(key   == this ? "(this Map)" : key);
        sb.append('=');
        sb.append(value == this ? "(this Map)" : value);
        if (! i.hasNext())
            return sb.append('}').toString();
        sb.append(',').append(' ');
    }
}
```

AbstractCollection의 toString() → ArrayList의 상위의 상위 추상 클래스
```
public String toString() {
    Iterator<E> it = iterator();
    if (! it.hasNext())
        return "[]";

    StringBuilder sb = new StringBuilder();
    sb.append('[');
    for (;;) {
        E e = it.next();
        sb.append(e == this ? "(this Collection)" : e);
        if (! it.hasNext())
            return sb.append(']').toString();
        sb.append(',').append(' ');
    }
}
```

** System.out.println()을 어떻게 테스트 해야하나?**
* ByteArrayOutputStream 객체를 생성해준 후, OutputStream을 set하면 됩니다.
* @AfterEach를 통해서 테스트가 끝나면 원래대로 되돌려줍시다.
```java
class ToStringTest {
    // Object 클래스의 toString 메서드를 호출했을 때
    private static final Pattern OBJECT_PATTERN = Pattern.compile("(.+)PhoneNumberByDefault@(.+)");
    
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }
    
    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }

    @DisplayName("System.out.println 메서드에서 객체 호출")
    @Test
    void callToString_Println() {
        PhoneNumberByDefault phoneNumber = new PhoneNumberByDefault("010", "1234", "1234");
        System.out.println(phoneNumber);

        assertThat(OBJECT_PATTERN.matcher(outContent.toString()).find()).isTrue();
    }
}
```

**주의할점**
* toString()을 재정의할 때 순환참조 조심 - jpa에서 자주일어남
* DE에서 지원해주는 toString() 혹은 Lombok에 있는 @ToString 을 무분별하게 사용하다가는 StackOverflowError 가 일어날 수 있습니다.