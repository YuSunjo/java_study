package chapter3.item10;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Member> members = new ArrayList<>();
        Member member = new Member("hello@naver.com", "hello");
        members.add(member);

        System.out.println(members.contains(member)); // true
    }

}
