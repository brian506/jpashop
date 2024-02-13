package jpabook.jpashop.service;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // 롤백하기 위한
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    // insert 쿼리를 실행했을때 볼 수 있다
    // db에 들어갔는지 확인하고 싶을 때 쓰면됨
    public void 회원가입() throws Exception {
        //given(조건이 주어졌을때)
        Member member = new Member();
        member.setName("choi");
        member.setEmail("brian506@naver.com");

        //when(조건을 실행했을 때)
        Long saveId = memberService.join(member);

        //then
        assertEquals(member,memberRepository.findOne(saveId));
        //멤버리포지토리에서 찾아온 멤버를 찾았을 때 위에서 세팅한 member와 똑같은 값이 나오는지 찾는 것
    }
    //테스트 돌릴때 h2 DB를 키고 나서 테스트를 돌려야 failed to load applicationcontext 에러가 안남

    @Test(expected = IllegalStateException.class) // expected로 예외발생을 잡아줄수있다
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");
        member1.setEmail("brian506@gmail.com"); // email 값도 세팅 되어야 Assertion 에러가 나지 않는다!

        Member member2 = new Member();
        member2.setName("kim"); // 같은 이름이 세팅되었을때
        member2.setEmail("brian506@gmail.com");

        //when
        memberService.join(member1);
        memberService.join(member2);

        //then
        fail("예외가 발생해야 한다.");

      }


}