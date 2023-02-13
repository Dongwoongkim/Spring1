package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeProviderTest {


    @Test
    void providerTest()
    {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean bean1 = ac.getBean(ClientBean.class);
        ClientBean bean2 = ac.getBean(ClientBean.class);
        int count1 = bean1.logic();
        int count2 = bean2.logic();
        System.out.println("count1 = " + count1);
        System.out.println("count2 = " + count2);
    }

    @Scope("singleton")
    static class ClientBean
    {
//        @Autowired private ApplicationContext ac;

//        @Autowired
//        private ObjectProvider<PrototypeBean> prototypeBeanProvider;

        @Autowired
        private ObjectFactory<PrototypeBean> prototypeBeanObjectFactory;

        public int logic()
        {
//            PrototypeBean bean = ac.getBean(PrototypeBean.class);
//            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            PrototypeBean prototypeBean = prototypeBeanObjectFactory.getObject();
            prototypeBean.addcount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean
    {
        private int count = 0;

        public void addcount()
        {
            count++;
        }

        public int getCount() {
            return count;
        }

    }
}
