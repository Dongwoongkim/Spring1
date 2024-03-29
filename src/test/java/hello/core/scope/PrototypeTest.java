package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void prototypeBeanTest()
    {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class);
        System.out.println("find prototypeBean1");
        ProtoTypeBean protoTypeBean1 = ac.getBean(ProtoTypeBean.class);
        System.out.println("find prototypeBean2");

        ProtoTypeBean protoTypeBean2 = ac.getBean(ProtoTypeBean.class);


        System.out.println("protoTypeBean1 = " + protoTypeBean1);
        System.out.println("protoTypeBean2 = " + protoTypeBean2);

        Assertions.assertThat(protoTypeBean1).isNotSameAs(protoTypeBean2);

        ac.close();
    }

    @Scope("prototype")
    static class ProtoTypeBean
    {
        @PostConstruct
        public void init()
        {
            System.out.println("ProtoTypeBean.init");
        }

        @PreDestroy
        public void destroy()
        {
            System.out.println("ProtoTypeBean.destroy");
        }
    }
}
