package com.blog.blogservice.service;

import com.blog.blogservice.controller.dto.request.LoginForm;
import com.blog.blogservice.controller.dto.request.MemberCreator;
import com.blog.blogservice.domain.Member;
import com.blog.blogservice.exception.IdOrPwMisMatch;
import com.blog.blogservice.repository.MemberRepository;
import lombok.Builder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;
    @PersistenceContext
    EntityManager em;

    MemberCreator request;
    LoginForm loginForm;
    Member newMember;
    @BeforeEach
    void initFixture(){
        loginForm = LoginForm.builder()
                .loginId("pll0123")
                .password("pll0123")
                .build();

        request = MemberCreator.builder()
                .loginId("pll0123")
                .password("pll0123")
                .name("정성훈")
                .build();

        newMember = Member.builder()
                .loginId(request.getLoginId())
                .name(request.getName())
                .password(request.getPassword())
                .build();

        memberRepository.save(newMember);

        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("로그인 테스트")
    void login() {

    }

}