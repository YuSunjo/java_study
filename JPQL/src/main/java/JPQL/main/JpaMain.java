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

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            String Myquery ="select m.username from Member m " +
                    "join m.team t " +
                    "join m.orders o where t.name='teamA'";

            //t.members -> 컬렉션이여서 탐색 불가능
//            String query = "select t.members From Team t";

            //탐색하고 싶으면 이렇게 join 해서 해야한다. -- from 절에서 명시적 조인
            String query = "select m.username From Team t join t.members m";

            em.createQuery(query)
                    .getResultList();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
