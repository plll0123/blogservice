//package com.blog.blogservice.service;
//
//import com.blog.blogservice.controller.dto.request.LoginForm;
//import com.blog.blogservice.controller.dto.request.MemberCreator;
//import com.blog.blogservice.domain.Member;
//import com.blog.blogservice.exception.IdOrPwMisMatch;
//import com.blog.blogservice.exception.MemberNotFoundException;
//import com.blog.blogservice.repository.MemberRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@SpringBootTest
//@Transactional
//class MemberServiceTest {
//
//    @Autowired
//    MemberRepository memberRepository;
//    @Autowired
//    MemberService memberService;
//    @PersistenceContext
//    EntityManager em;
//
//    MemberCreator memberCreator;
//    LoginForm loginForm;
//    Member newMember;
//
//    @BeforeEach
//    void initFixture() {
//        loginForm = LoginForm.builder()
//                .loginId("test")
//                .password("test")
//                .build();
//
//        memberCreator = MemberCreator.builder()
//                .loginId("test")
//                .password("test")
//                .name("tester")
//                .build();
//
//        newMember = Member.builder()
//                .loginId(memberCreator.getLoginId())
//                .name(memberCreator.getName())
//                .password(memberCreator.getPassword())
//                .build();
//
////        memberRepository.save(newMember);
//
//        em.flush();
//        em.clear();
//    }
//
//    @Test
//    @DisplayName("회원 가입 후 로그인 테스트")
//    @Transactional
//    void joinTest() {
//        //given
//        Long joinMemberId = memberService.join(memberCreator);
//
//        em.flush();
//        em.clear();
//
//        Long byLoginId = memberService.findIdByLoginId(memberCreator.getLoginId());
//        //expected
//        Member joinMember = memberRepository.findById(joinMemberId)
//                .orElse(null);
//        Member loginMember = memberRepository.findById(byLoginId)
//                .orElse(null);
//
//        assertThat(joinMember.getId()).isEqualTo(loginMember.getId());
//    }
//
//    @Test
//    @DisplayName("[negative] - 로그인 요청 시 IdOrPwMisMatch 예외가 발생하는 두 가지 케이스 테스트")
//    void login_negative() {
//        //given
//        memberRepository.save(newMember);
//
//        loginForm.setLoginId("fail id");
//        //expected
//        assertThrows(IdOrPwMisMatch.class, () -> memberService.login(loginForm));
//
//        loginForm.setLoginId("test");
//        loginForm.setPassword("fail pw");
//        assertThrows(IdOrPwMisMatch.class, () -> memberService.login(loginForm));
//    }
//
//    @Test
//    @DisplayName("[negative] - 회원 id로 찾았을 때 일치하는 회원이 없으면 예외가 발생한다.")
//    void findById_negative() {
//        //given
//        assertThrows(MemberNotFoundException.class,
//                () -> memberService.findById(9999999999L));
//    }
//
//}