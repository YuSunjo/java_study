package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    //mappedBy => 반대편에 어떤 필드가 걸려있는지
    //team에 의해 관리가됨     mappedBy가 있는 곳은 읽기만 가능
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    //일대다 단방향  -> 일쪽이 연관관계 주인
//    @OneToMany
//    @JoinColumn
//    private List<Member> members = new ArrayList<>();

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
