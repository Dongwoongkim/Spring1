package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImp;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService()
    {
        return new MemberServiceImp(getMemberRepository());
    }

    @Bean
    public MemberRepository getMemberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService()
    {
        return new OrderServiceImp(getMemberRepository(), getPolicy());
    }

    @Bean
    public DiscountPolicy getPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }


}
