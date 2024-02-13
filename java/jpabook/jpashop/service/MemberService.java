package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // 생성자 injection을 롬복으로 사용
public class MemberService {
    // 회원가입이 중복이 되었을때의 예외
    // 가입 후 조회하기 위한 메서드

    private final MemberRepository memberRepository;
    //final 키워드를 추가하면 컴파일 시점에 memberRepository를 설정하지 않는 오류를 체크할 수 있다

    //회원 가입
    @Transactional
    public Long join(Member member){
        validateDuplication(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplication(Member member){
        List<Member> findMembers = memberRepository.findByName(member.getName());//리포지토리에서 같은 이름이 있는지 찾아보는 것
        List<Member> findEmails = memberRepository.findByEmail(member.getEmail());// 같은 ID가 있는지 찾아보는 것
        //getter setter 메소드 명 다음엔 반드시 대문자로 작성

        if (!findMembers.isEmpty() && !findEmails.isEmpty() ){ // 중복되는 회원이 있을 떄의 예외
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }
    //회원 전체 조회
    @Transactional(readOnly = true) // 조회(읽기)일때만 readOnly=true를 넣으면 성능 최적화
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    //회원 한명 조회
    @Transactional(readOnly = true)
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
