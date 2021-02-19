package embedded;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Member2 {

    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Embedded
    private Period wordPeriod;

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

    public Period getWordPeriod() {
        return wordPeriod;
    }

    public void setWordPeriod(Period wordPeriod) {
        this.wordPeriod = wordPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Address getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(Address workAddress) {
        this.workAddress = workAddress;
    }

    @Embedded
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column"WORK_CITY"),
            @AttributeOverride(name = "street", column = @Column"WORK_STREET"),
            @AttributeOverride(name = "zipcode", column = @Column"WORK_ZIPCODE")
    })
    private Address workAddress;

}
