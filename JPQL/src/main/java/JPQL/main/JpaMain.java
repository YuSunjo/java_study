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

            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);

            em.flush();
            em.clear();

//            String query = "select m From Member m";
            //패치 조인 -> 한방 쿼리로 그냥 한번에 join해서 다 가져옴 - 지연로딩X
//            String query = "select m From Member m join fetch m.team";

            //컬랙션 패치조인 ->
//            String query = "select t from Team t join fetch t.members";

            //컬렉션 패치조인 중복 제거
//            String query = "select distinct t from Team t join fetch t.members";

            //패치 조인의 한계 -> 별칭을 주면X
            String query = "select t from Team t join fetch t.members m";

            List<Team> resultList = em.createQuery(query, Team.class)
                    .getResultList();



//            for (Member member : resultList) {
//                System.out.println("Member="+ member);
//            }
//            for (Member member : resultList) {
//                System.out.println("Member="+ member.getUsername() + ","+ member.getTeam().getName());
//                //회원 1, 팀A(SQL)
//                //회원 2, 팀A(1차 캐시)
//                //회원 3, 팀B(SQL)
//
//                //문제점 - 회원 100명이면 100개 넘게 쿼리가 나가서 문제 -- 페치조인 해야함.
//            }

            for (Team team : resultList) {
                System.out.println("team=" + team.getName() + " | " + team.getMembers().size());
                for (Member member: team.getMembers()) {
                    System.out.println("-> member = " + member);
                }
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
