package com.study.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.querydsl.dto.MemberDto;
import com.study.querydsl.entity.Member;
import com.study.querydsl.entity.QMember;
import com.study.querydsl.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static com.study.querydsl.entity.QMember.member;
import static com.study.querydsl.entity.QTeam.team;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.iterable;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);
        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @Test
    public void startJPQL() {

//member1을 찾아라.
        String qlString =
                "select m from Member m " +
                        "where m.username = :username";
        Member findMember = em.createQuery(qlString, Member.class)
                .setParameter("username", "member1")
                .getSingleResult();
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void startQuerydsl() {
//member1을 찾아라.
        queryFactory = new JPAQueryFactory(em);
        Member findMember = queryFactory
                .select(member)
                .from(member)
                .where(member.username.eq("member1"))//파라미터 바인딩 처리
                .fetchOne();
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void search() {
        queryFactory = new JPAQueryFactory(em);
        Member findMember = queryFactory
                .selectFrom(member)
//                .where(member.username.eq("member1")
//                        .and(member.age.eq(10)))
                .where(
                        member.username.eq("member1"),
                        member.age.eq(10)
                )
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void resultFetch() {
        queryFactory = new JPAQueryFactory(em);
        List<Member> fetch = queryFactory
                .selectFrom(member)
                .fetch();

        QueryResults<Member> results = queryFactory.selectFrom(member).fetchResults();

        results.getTotal();

        List<Member> content = results.getResults();

        queryFactory.selectFrom(member).fetchCount();
    }

    @Test
    public void paging1() {
        queryFactory = new JPAQueryFactory(em);
        List<Member> results = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1)
                .limit(2)
                .fetch();

        assertThat(results.size()).isEqualTo(2);
    }

    @Test
    public void paging2() {
        queryFactory = new JPAQueryFactory(em);
        QueryResults<Member> queryResults = queryFactory.selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1)
                .limit(2)
                .fetchResults();

        assertThat(queryResults.getTotal()).isEqualTo(4);
        assertThat(queryResults.getLimit()).isEqualTo(2);
        assertThat(queryResults.getOffset()).isEqualTo(1);
        assertThat(queryResults.getResults().size()).isEqualTo(2);
    }

    @Test
    public void aggregation() {
        queryFactory = new JPAQueryFactory(em);
        List<Tuple> result = queryFactory.select(
                member.count(),
                member.age.sum(),
                member.age.avg(),
                member.age.max(),
                member.age.min()
        ).from(member).fetch();

        Tuple tuple = result.get(0);
        assertThat(tuple.get(member.count())).isEqualTo(4);
    }

    @Test
    public void group() {
        queryFactory = new JPAQueryFactory(em);
        List<Tuple> result = queryFactory
                .select(team.name, member.age.avg())
                .from(member)
                .join(member.team, team)
                .groupBy(team.name) //having 도 사용가능
                .fetch();
        Tuple teamA = result.get(0);
        Tuple teamB = result.get(1);
    }

    /**
     * 팀 A에 소속된 모든 회원
     */
    @Test
    public void join() {
        queryFactory = new JPAQueryFactory(em);
        List<Member> result = queryFactory.selectFrom(member)
                .join(member.team, team)
                .where(team.name.eq("teamA"))
                .fetch();

        assertThat(result)
                .extracting("username")
                .containsExactly("member1", "member2");
    }

    /**
     * 세타 조인
     * 회원의 이름이 팀 이름과 같은 회원 조회
     */
    @Test
    public void theta_join() {
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));

        queryFactory = new JPAQueryFactory(em);
        queryFactory
                .select(member)
                .from(member, team)
                .where(member.username.eq(team.name))
                .fetch();
    }


    /**
     * ex) 회원과 팀을 조인하면서, 팀 이름이 teamA인 팀만 조인, 회원은 모두 조회
     * select m from Member m left join m.team t on t.name = 'teamA'
     */
    @Test
    public void join_on_filtering() {
        queryFactory = new JPAQueryFactory(em);
        List<Tuple> result = queryFactory
                .select(member, team)
                .from(member)
                .leftJoin(member.team, team)
                //내부조인일 경우 -> where 하고 on 하고 똑같음, 외부 조인일 경우에만 on사용
//                .where(team.name.eq("teamA"))
                .on(team.name.eq("teamA"))
                .fetch();

        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
    }

    /**
     * 연관관계가 없는 엔티티 외부 조인
     */
    @Test
    public void join_on_no_relation() {
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));
        em.persist(new Member("teamC"));

        queryFactory = new JPAQueryFactory(em);
        List<Tuple> result = queryFactory
                .select(member, team)
                .from(member)
                .leftJoin(team).on(member.username.eq(team.name))
                .fetch();

        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
    }

    @Test
    public void fetchJoinNo() {
        em.flush();
        em.clear();

        queryFactory = new JPAQueryFactory(em);
        Member member1 = queryFactory
                .selectFrom(member)
                .join(member.team, team)
                .where(member.username.eq("member1"))
                .fetchOne();
    }

    @Test
    public void fetchJoinUse() {
        em.flush();
        em.clear();

        queryFactory = new JPAQueryFactory(em);
        Member member1 = queryFactory
                .selectFrom(member)
                .join(member.team, team).fetchJoin()
                .where(member.username.eq("member1"))
                .fetchOne();
    }

    /**
     * 나이가 가장 많은 회원 조회
     */
    @Test
    public void subQuery() {

        QMember memberSub = new QMember("memberSub");
        queryFactory = new JPAQueryFactory(em);
        queryFactory.selectFrom(member)
                .where(member.age.eq(
                        JPAExpressions
                        .select(memberSub.age.max())
                        .from(memberSub)
                ))
                .fetch();
    }

    @Test
    public void selectSubQuery() {

        QMember memberSub = new QMember("memberSub");
        queryFactory = new JPAQueryFactory(em);
        queryFactory.select(member.username,
                JPAExpressions.select(memberSub.age.avg()).from(memberSub)
                ).from(member)
                .fetch();
    }

    @Test
    public void tupleProjection() {
        queryFactory = new JPAQueryFactory(em);
        List<Tuple> result = queryFactory
                .select(member.username, member.age)
                .from(member)
                .fetch();
    }

    @Test
    public void findDtoByJPQL() {
        em.createQuery("select new com.study.querydsl.dto.MemberDto(m.username, m.age) from Member m", Member.class);
    }

    @Test
    public void findDtoBySetter() {
        queryFactory = new JPAQueryFactory(em);
        List<MemberDto> result = queryFactory
                .select(Projections.bean(MemberDto.class,
                        member.username,
                        member.age))
                .from(member)
                .fetch();
    }

    //필드명이 맞아야지 들어감   member.username.as("name") 이렇게 해도됨
    @Test
    public void findDtoByField() {
        queryFactory = new JPAQueryFactory(em);
        List<MemberDto> result = queryFactory
                .select(Projections.fields(MemberDto.class,
                        member.username,
                        member.age))
                .from(member)
                .fetch();
    }



}
