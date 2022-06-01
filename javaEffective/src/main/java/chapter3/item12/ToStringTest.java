package chapter3.item12;

public class ToStringTest {

    private String name;
    private String age;

    @Override
    public String toString() {
        return "ToStringTest{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

}
