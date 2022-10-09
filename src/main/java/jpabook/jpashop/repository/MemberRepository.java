package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository// Sring을 사용하기 때문에 컴포넌트 스캔에 의해서 자동으로 스프링 빈으로 관리
public class MemberRepository {

    @PersistenceContext// 스프링이 em을 만들어서 주입해줍니다.
    private EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class,id);
    }

    public List<Member> findAll(){
        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();

        return result;
    }
    public List<Member> findByName(String name){
        List<Member> result = em.createQuery("select m from Member m where m.name =:name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result;
    }


}
