package hellojpa;

import hellojpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String arg[]){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member= new Member();
            member.setId(100L);
            member.setName("안녕");

            em.persist(member); //영구저장하다!
            tx.commit();
            System.out.println("Success");
        }catch (Exception e){
            tx.rollback();
            System.out.println("Fail");
        }finally {
            em.close();//엔티티 매니저는 사용후 꼭 닫아야 함.
        }

        emf.close();
    }
}
