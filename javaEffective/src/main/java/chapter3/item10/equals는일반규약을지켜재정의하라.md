### equals 는 일반 규약을 지켜 재정의하라

* equals 메서드 재정의는 곳곳에 함정이 도사리고 있어 끔찍한 결과를 초래한다.
* 따라서 아예 재정의를 하지 않는 것이 더 좋을 때도 있다.

### equals 재정의 하지 않는 경우

**각 인스턴스가 본질적으로 고유할 경우**
* 값을 표현하는 것이 아니라 동작하는 개체를 표현하는 클래스일 경우를 말한다. - Thread

**인스턴스의 '논리적 동치성'을 검사할 일이 없을 경우**

**상위 클래스에서 재정의한 equals가 하위 클래스에도 딱 들어맞는 경우**

* List의 대부분은 equals 메서드가 재정의 되어 있고 이것을 사용
```java
public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {
	public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof List))
            return false;

        ListIterator<E> e1 = listIterator();
        ListIterator<?> e2 = ((List<?>) o).listIterator();
        while (e1.hasNext() && e2.hasNext()) {
            E o1 = e1.next();
            Object o2 = e2.next();
            if (!(o1==null ? o2==null : o1.equals(o2)))
                return false;
        }
        return !(e1.hasNext() || e2.hasNext());
    }
}
```

**클래스가 private이거나 package-private이고 equals 메서드를 호출할 일이 없을 경우**

* equals 자체를 호출하는 걸 막고 싶다면 아래와 같이 하는것이 좋다
```java
public class PackagePrivate {
    @Override
    public boolean equals(Object obj) {
        throw new AssertionError();
    }
}
```
**인스턴스 통제 클래스**
* 인스턴스가 2개 이상 만들어지지 않기 때문에 논리적 동치성과 객체 식별성이 같은 의미가 되기 때문이다.
* Enum, 싱글톤

### equals 메서드를 재정의를 해야하는 경우

* 객체 식별성이 아니라 논리적 동치성을 확인해야 하는데, 상위 클래스의 equals가 논리적 동치성을 비교하도록 재정의되지 않았을 때다.
* 대표적으로 값이 같은지?
* 이러한 점을 이용해 Map 키와 Set 원소로 사용할 수 있게 된다.

### equals 메서드 일반 규약

* equals 메서드는 동치 관계를 구현하며, 다음을 만족한다.
* 여기서 모든 참조값은 null이 아닐 때이다.

**반사성**
```
x.equlas(x) == true
```
* 자기 자신과 같아야 한다.
```
@Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        return this == o;
    }
```
```java
public class Main {

    public static void main(String[] args) {
        List<Member> members = new ArrayList<>();
        Member member = new Member("hello@naver.com", "hello");
        members.add(member);

        System.out.println(members.contains(member)); // true
    }
    
}
```

**대칭성**
* 서로에 대한 동치 여부에 똑같이 답해야 한다는 뜻입니다.
```
x.equals(y) == y.equals(x)
```
```
@Override
public boolean equals(Object o) {
	if (null == o) {
		return false;
	}
	if (o instanceof Member) {
		return email.equalsIgnoreCase(((Member)o).email);
	}
	if (o instanceof String) {
		return email.equalsIgnoreCase((String)o);
	}
	return false;
}
```
```java
public class Main {

    public static void main(String[] args) {
        Member rutgo = new Member("ksy90101", "rutgo", 29);
        Member seyun = new Member("ksy90101", "seyun", 29);
        String email = "ksy90101";
        System.out.println(rutgo.equals(seyun)); // true
        System.out.println(rutgo.equals(email)); // true
//        System.out.println(email.equals(rutgo)); // false
    }
}
```
* String의 equals는 Member의 존재를 모른다. - 대칭성 위반

* 반사성과 같이 컬렉션으로 테스트
```java
public class Main {

    public static void main(String[] args) {
        List<Member> members = new ArrayList<>();
        Member rutgo = new Member("ksy90101", "rutgo", 29);
        Member seyun = new Member("ksy90101", "seyun", 29);
        String email = "ksy90101";

        members.add(rutgo);

        System.out.println(members.contains(email)); // false
        System.out.println(members.contains(seyun)); // true
    }
}
```
* 지금과 같은 경우에는 false가 나오게 된다. 이건 단순히 OpenJDK의 구현방식 때문이다.
```
public boolean contains(Object o) {
    Iterator<E> it = iterator();
    if (o==null) {
        while (it.hasNext())
            if (it.next()==null)
                return true;
    } else {
        while (it.hasNext())
            if (o.equals(it.next()))
			return true;
		}
		return false;
}
```
* 위에서 o.equals => String.equals 를 사용하게 되어서 false가 나옴
* it.next().equals(o); 로 변경하게 된다면 true가 나옴
* String과 Member의 equals()를 연동하겠다 라고 생각하지 말자

**추이성(transitivity)**
```
x.equals(y) == true
y.equals(z) == true
x.equals(z) == true
```

**일관성(consistency)**
```
x.equals(y) == true;
x.equals(y) == true;
```

### equals 메서드 구현 방법 단계별 정리
* == 연산자를 사용해 입력이 자기 자신의 참조인지 확인한다.
  * 단순한 성능 최적화용으로 비교 작업이 복잡한 상황일 때 값어치를 한다.
* instanceof 연산자로 입력이 올바른 타입인지 확인한다.
  * 묵시적 null 체크를 할 수 있다.
  * 또한 Collection interface처럼 특정 인터페이스의 타입 체크를 할 수도 있다.
* 입력을 올바른 타입으로 형변환한다.
  * instanceof 검사를 했기 때문에 100% 성공한다.
* 입력 객체와 자기 자신의 대응되는 핵심 필드들이 모두 일치하는지 하나씩 검사한다.

**완성코드**
```
@Override
public boolean equals(Object o) {
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    Member member = (Member)o;
    return email.equals(member.email) && name.equals(member.name) && age.equals(member.age);
}
```

### 주의사항
* 기본 타입은 == 으로 비교하고 그 중 double, float는 Double.compare(), Float.compare()을 이용해 검사해야 한다. 이유는 부동소수점을 다뤄야 하기 때문이다.
* 배열의 모든 원소가 핵심 필드이면 Arrays.equals 메서드들 중 하나를 사용하자.
* null이 정상 값으로 취급할 때는 OBjects.equals()를 이용해 NPE를 방지하자.
* 최상의 성능을 바란다면 다를 가능성이 더 크거나 비교하는 비용이 싼 필드를 먼저 비교하자.
* 동기화용 락 필드 같이 객체의 논리적 상태와 관련 없는 필드는 비굫사면 안된다.
* equals를 재정의할 땐 hashCode도 반드시 재정의 하자
* Object 타입을 매개변수로 받는 equals 메서드는 선언하지 말자.

=> 필요할 때만 재정의를 하고 구현했다면 대칭성, 추이성, 일관성 확인해보자