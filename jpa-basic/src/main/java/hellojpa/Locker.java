package hellojpa;

import javax.persistence.*;

@Entity
public class Locker {
    @Id @GeneratedValue
    @Column(name= "MEMBER_ID")
    private Long id;

    @Column()
    private String name;

    @OneToOne(mappedBy = "locker")
    private Member member;
}
