package hellojpa;

import hellojpa.entity.Member;
import hellojpa.entity.MemberType;
import hellojpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String arg[]){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member= new Member();
            member.setName("hi");
            member.setTeam(team);
            em.persist(member); //영구저장하다!

            em.flush(); //강제로 DB 반영 시킴 (콘솔에서 DB 반영되는 과정을 보기 위함)
            em.clear();

            //단방향 매핑
            Member findMember = em.find(Member.class, member.getId()); //ManytoOne 무조건 Team 테이블을 join 함. fetch=FetchType.LAZY 설정 필요.
            Team findTeam = findMember.getTeam(); //지연로딩 시, Team을 가져올 때에만 team 테이블을 탐색한다.
            //양방향 매핑
            //findTeam = em.find(Team.class,team.getId());
            findTeam.getName(); //역방향 조회

            List<Member> members = findTeam.getMembers();
            for(Member member1 : members){
                System.out.println("member 1 = "+member1);
            }
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
