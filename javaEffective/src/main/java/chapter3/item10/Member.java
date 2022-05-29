package chapter3.item10;

import java.util.Objects;

public class Member {

    private String email;
    private String name;

    public Member(String email, String name) {
        this.email = email;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(email, member.email) && Objects.equals(name, member.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name);
    }

}
