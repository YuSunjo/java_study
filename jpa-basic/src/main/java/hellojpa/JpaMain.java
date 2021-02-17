package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team);
            em.persist(member);

            //연관관계 주인이 아니여서 수정 삽입이 불가능*****
            //하지만 적어줘야함..  1. 객체지향적이여야 하니까
            // 2. 테스트 케이스 작성하기 위해
            //편의 메서드를 생성해서 거기에 넣음(setTeam())에 team.getMembers().add(member);를 넣음
            //team.getMembers().add(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            //양방향 연관관계
            List<Member> members = findMember.getTeam().getMembers();





            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
