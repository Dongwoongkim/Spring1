package hello.core.AutoWiredTest;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllMyBeanTest {

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;


        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }
        public int getDiscountPrice(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member,price);
        }
    }

    @Test
    void AllBeansTest()
    {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(AutoAppConfig.class,DiscountService.class);

        Member member = new Member(1L,"kdo6301", Grade.VIP);
        MemberService memberService = ac.getBean(MemberService.class);
        memberService.join(member);
        DiscountService discountService = ac.getBean(DiscountService.class);
        int discountPrice = discountService.getDiscountPrice(member,10000,"fixDiscountPolicy");

        Assertions.assertThat(discountPrice).isEqualTo(1000);

    }
}
