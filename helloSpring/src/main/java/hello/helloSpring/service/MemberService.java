package hello.helloSpring.service;

import hello.helloSpring.domain.Member;
import hello.helloSpring.repository.MemberRepository;
import hello.helloSpring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /*
    회원 가입
     */
    public Long join(Member member){
        // 중복 이름 허용 x
        validateDuplicatedMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicatedMember (Member member){
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    try {
                        throw new IllegalStateException("이미 존재하는 회원입니다.");
                    }
                    catch (IllegalStateException e) {
                        throw new IllegalStateException("이미 존재하는 회원입니다.");
                    }
                });
    }

    /*
       전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
