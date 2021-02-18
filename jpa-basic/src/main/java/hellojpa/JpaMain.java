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

            Movie movie = new Movie();
            movie.setDirector("a");
            movie.setActor("bbb");
            movie.setName("sdfsdf");
            movie.setPrice(10000);

            em.persist(movie);

            em.flush();
            em.clear();

            //조회할때 알아서 조인해서 가져옴옴
            Movie findovie = em.find(Movie.class, movie.getId());


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
