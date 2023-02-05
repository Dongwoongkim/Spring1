package hello.core.scan;
import hello.core.AppConfig;
import hello.core.AutoAppConfig;
import hello.core.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImp;
import hello.core.order.Order;
import hello.core.order.OrderServiceImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AutoAppConfigTest {

    @Test
    void basicBean()
    {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        String [] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String s : beanDefinitionNames) {
            System.out.println("beanDefinitionName = " + s);
        }

        MemberService bean = ac.getBean(MemberService.class);
        Assertions.assertThat(bean).isInstanceOf(MemberServiceImp.class);

        OrderServiceImp bean1 = ac.getBean(OrderServiceImp.class);
        System.out.println("bean1 = " + bean1);
    }

}
