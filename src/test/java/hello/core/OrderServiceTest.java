package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImp;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;
import org.springframework.boot.system.ApplicationPid;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder()
    {
        // given
        Long memberId = 1L;
        Member member = new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member);

        // when
        Order order = orderService.createOrder(memberId, "itemA", 100000);

        // then
        assertThat(order.getDiscountPrice()).isEqualTo(10000);
    }


    @Test
    void filedInjectionTest()
    {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }

        OrderService orderService1 = ac.getBean(OrderService.class);
        Member member = new Member(1L,"kimdongwoong",Grade.VIP);
        MemberRepository memberRepository = ac.getBean(MemberRepository.class);
        memberRepository.save(member);
        Order itemA = orderService1.createOrder(1L, "itemA", 1000);
        System.out.println(itemA);
    }


}
