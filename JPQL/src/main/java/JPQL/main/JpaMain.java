package JPQL.main;

import JPQL.Member;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            //typeQuery와 Query
            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
            Query query1 = em.createQuery("select m.username, m.age from Member m");

            List<Member> result = query.getResultList();
            Member singleResult = query.getSingleResult();

            // 파라미터 바인딩
            Member result2 = em.createQuery("select m from Member m where m.username = :username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
