package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    static class TestConfig
    {
        @Bean
        public StatefulService statefulService()
        {
            return new StatefulService();
        }
    }

    @Test
    void statefulServiceSingleton()
    {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = applicationContext.getBean(StatefulService.class);
        StatefulService statefulService2 = applicationContext.getBean(StatefulService.class);

        //Thread A : A사용자 1000원 주문
        statefulService1.order("userA",1000);

        //Thread B : B사용자 2000원 주문
        statefulService2.order("userB",2000);

        //Thread A : A사용자 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

    }
}