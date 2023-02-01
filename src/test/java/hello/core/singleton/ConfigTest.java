package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImp;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigTest {

    @Test
    void configTest()
    {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImp memberService = ac.getBean(MemberServiceImp.class);
        OrderServiceImp orderService = ac.getBean(OrderServiceImp.class);

        System.out.println("MemberService memberRepo : " + memberService.getMemberRepository());
        System.out.println("orderService  memberRepo : " + orderService.getMemberRepository());
    }


    @Test
    void configurationDeep()
    {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("bean = " + bean.getClass());
        // -> bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$ad8aea39
    }
}
