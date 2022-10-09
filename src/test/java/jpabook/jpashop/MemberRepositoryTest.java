package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private  MemberRepository memberRepository;

    @Test
    @Transactional
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

        // 테스트 단에 @Transactional 을 하면 테스트가 끝나면 바로 rollback 해줌
    }


}