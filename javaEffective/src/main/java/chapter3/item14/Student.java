package chapter3.item14;

import java.util.Comparator;

public class Student implements Comparable<Student> {

//    private int grade;
//    private String name;
//    private int age;
//
//    @Override
//    public int compareTo(Student o) {
//        int result = Integer.compare(grade, o.grade);
//
//        if (result == 0) {
//            result = CharSequence.compare(name, o.name);
//            if (result == 0) {
//                result = Integer.compare(age, o.age);
//            }
//        }
//        return result;
//    }

    private static final Comparator<Student> COMPARATOR =
            Comparator.comparingInt((Student student) -> student.grade)
                    .thenComparing((Student student) -> student.name)
                    .thenComparingInt((Student student) -> student.age);

    private int grade;
    private String name;
    private int age;

    @Override
    public int compareTo(Student o) {
        return COMPARATOR.compare(this, o);
    }

}
