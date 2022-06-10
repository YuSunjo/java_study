### public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라

```java
class Point {
    public double x;
    public double y;
}
```
* 데이터 필드에 직접 접근 가능 -> 캡슐화 이점 X
* API를 수정하지 않고는 내부 표현을 바꿀 수 없고, 불변식을 보장할 수 없으며, 외부에서 필드에 접근할 때 부수 작업을 수행할 수도 없다.

```java
class Point {
    private double x;
    private double y;
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
}
```
* 메서드를 활용해 데이터를 캡슐화
* 패키지 바깥에서 접근할 수 있는 클래스라면 접근자를 제공함으로써 클래스 내부 표현 방식을 언제든 바꿀 수 있는 유연성을 얻을 수 있다.

* package-private 클래스 혹은 private 중첩 클래스라면 데이터 필드를 노출한다 해도 하등의 문제가 없다.
* 각각 패키지/클래스 내부에서만 동작하는 코드이기 때문에 데이터 필드가 노출되어도 무관 - 오히려 접근자 방식보다 깔끔

잘못된 사례
java.awt.package의 Point와 Dimension 클래스 (아이템67)
* 필드를 외부로 직접 노출
* 불변성을 보장할 수 없음

```java
public final class Time {
    private static final int HOURS_PER_DAY = 24;
    private static final int MINUTES_PER_HOURS = 60;

    public final int hour;
    public final int minute;

    public Time(int hour, int minute) {
        if (hour < 0 || hour >= HOURS_PER_DAY)
            throw new IllegalArgumentException("시간: " + hour);
        if (minute < 0 || minute >= MINUTES_PER_HOURS)
            throw new IllegalArgumentException(": " + minute);
        this.hour = hour;
        this.minute = minute;
    }
}
```
* api를 변경하지 않고는 내부 표현 변경 불가
* 외부에서 필드 접근 시 부수 작업 수행 불가

### 결론
* public 클래스는 가변 필드 노출되지 않도록
* 불변필드라면 노출해도 되지만 조심
* 예외적으로 package-private, private 중첩 클래스에서는 불변/가변 여부와 관계없이 필드를 노출하는 편이 나은 경우가 있다.