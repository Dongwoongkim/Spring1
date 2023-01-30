package hello.core.member;

import hello.core.MemberRepository;

public class MemberServiceImp implements MemberService{
    public final MemberRepository memberRepository;

    public MemberServiceImp(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void print()
    {
        System.out.println(this.memberRepository);
    }


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
