package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private  MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    void save() throws Exception{
        //given
        Member member = new Member();
        member.setUsername("codej");

        //when
        Long saveId = memberRepository.save(member);

        //then
        Member findMember = memberRepository.find(saveId);

        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);
        // 테스트 단에 @Transactional 을 하면 테스트가 끝나면 바로 rollback 해줌
        // findMember 와 member 의 Equals를 비교하면 같다.
        // 같은 트랜잭션 안에서 저장을하고 조회하면 영속성 컨텍스트가 같고,
        // 같은 영속성 안에서는 id값이 같으면 같은 엔티티
        }


}