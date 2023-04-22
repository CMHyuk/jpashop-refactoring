package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.exception.MemberNotFound;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.request.MemberForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    public void 회원가입() {
        //given
        MemberForm form = new MemberForm();
        form.setName("kim");

        //when
        Long savedId = memberService.join(form);
        Member findMember = memberRepository.findById(savedId)
                .orElseThrow(MemberNotFound::new);

        //then
        assertEquals(form.getName(), findMember.getName());
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() {
        //given
        MemberForm form1 = new MemberForm();
        form1.setName("kim");

        MemberForm form2 = new MemberForm();
        form2.setName("kim");

        //when
        memberService.join(form1);
        memberService.join(form2); //예외가 발생해야 한다!!!

        //then
        fail("예외가 발생해야 한다.");
    }
}