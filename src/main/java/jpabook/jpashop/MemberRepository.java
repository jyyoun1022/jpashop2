package jpabook.jpashop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;
    // 스프링 부트가 엔티티메니저를 주입을 해줍니다.

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Member find (Long id){
        return em.find(Member.class,id);
    }


}
