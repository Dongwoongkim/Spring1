package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName()
    {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImp.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2()
    {
        MemberService memberService = ac.getBean("memberService", MemberServiceImp.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImp.class);
    }

    @Test
    @DisplayName("타입으로만 조회")
    void findBeanByType()
    {
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImp.class);
    }


    @Test
    @DisplayName("빈 이름으로 조회 (실패)")
    void findBeanByNameX()
    {
        // MemberService xxxxxx = ac.getBean("xxxxxx", MemberService.class);

        // 오른쪽에있는 로직실행시 왼쪽Exception 발생해야함
        assertThrows(NoSuchBeanDefinitionException.class, ()->
                ac.getBean("xxxxxx", MemberService.class));

    }
}
