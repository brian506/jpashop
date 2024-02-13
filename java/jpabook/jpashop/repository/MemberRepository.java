package jpabook.jpashop.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    //회원 저장하는 공간 코드 작성한 것
    //엔티티 객체를 조회하기 위한 것
    @PersistenceContext //em에 엔티티매니저 주입시킨다
    private final EntityManager em;

    public void save(Member member){
        em.persist(member); //멤버 저장
    }

    public Member findOne(Long id){
       return em.find(Member.class,id); // 멤버 반환
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class) //jpql, member.class는 조회타입
                .getResultList();
    }

    public List<Member> findByName(String name){ // 이름을 통해서 멤버 조회
        return  em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();
    }

    public List<Member> findByEmail(String email){ // 특정 ID를 통해서 멤버 조회
        return em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email",email)
                .getResultList();
    }
}
