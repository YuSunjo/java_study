package hellojpa;

import javax.persistence.*;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED)     //이거 없으면 그냥 한 테이블에 다 생김
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //단일 테이블 전략
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)   //구현 테이블 마다 -- Item테이블이 안생김, 안쓰임
//@DiscriminatorColumn                           //Dtype까지 생성해줌,   단일테이블 전략에선 꼭 필요
public abstract class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
