package JPQL.main;

import JPQL.Member;
import JPQL.MemberDto;
import JPQL.Team;

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

//            em.createQuery("select m.team from Member m", Team.class)
//                    .getResultList();
//            em.createQuery("select t from Member m join m.team t", Team.class);

            //Objcet[]로 여러값 조회
//            List<Object[]> resultList = em.createQuery("select m.username, m.age from Member m")
//                    .getResultList();
//            Object[] result = resultList.get(0);

            //new 로 여러값 조회회
            List<MemberDto> resultList = em.createQuery("select new jpql.MemberDto(m.username, m.age) from Member m", MemberDto.class)
                    .getResultList();

            MemberDto memberDto = resultList.get(0);


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
