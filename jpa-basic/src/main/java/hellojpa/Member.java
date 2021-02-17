package hellojpa;

import org.hibernate.annotations.SortComparator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name= "username")
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    //외래키가 있는 곳을 주인**
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    //다대다 대신에 새로운 테이블 생성해서 oneToMany, manyToOne으로 함
    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    public Team getTeam() {
        return team;
    }

    public void changeTeam(Team team) {
        this.team = team;

        team.getMembers().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
