package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImp;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImp;

public class AppConfig {

    public MemberService memberService()
    {
        return new MemberServiceImp(new MemoryMemberRepository());
    }

    public OrderService orderService()
    {
        return new OrderServiceImp(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
