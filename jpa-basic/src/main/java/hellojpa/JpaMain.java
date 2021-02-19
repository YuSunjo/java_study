package hellojpa;

import embedded.Address;
import embedded.Member2;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.SQLOutput;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        //db 연결
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Address address = new Address("city", "street", "1000");

            Member2 member = new Member2();
            member.setUsername("member1");
            member.setHomeAddress(address);
            em.persist(member);

            Address address1 = new Address(address.getCity(), address.getStreet(), address.getZipcode());


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
