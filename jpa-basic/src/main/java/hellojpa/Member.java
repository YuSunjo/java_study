package hellojpa;

import org.hibernate.annotations.SortComparator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name= "username")
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    //프록시 객체로 조회
    @ManyToOne(fetch = FetchType.LAZY)
    //즉시 로딩  join해서 member team 까지 같이 가지고옴
//    @ManyToOne(fetch = FetchType.EAGER)
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

    public void setTeam(Team team) {
        this.team = team;
    }
}
