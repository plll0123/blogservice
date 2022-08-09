package com.blog.blogservice.repository;

import com.blog.blogservice.controller.dto.request.MemberCreator;
import com.blog.blogservice.domain.Member;
import com.blog.blogservice.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Optional;

import static com.blog.blogservice.domain.RoleType.USER;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;
    MemberCreator request;
    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    EntityManager em;

    @BeforeEach
    void initFixture() {
        request = MemberCreator.builder()
                .loginId("pll0123")
                .password("pll0123")
                .name("정성훈")
                .build();
    }
    
    @Test
    @DisplayName("회원 CRUD")
    void create() {

        Member newMember = Member.builder()
                .loginId(request.getLoginId())
                .name(request.getName())
                .password(request.getPassword())
                .build();

        assertThat(newMember.getRoleType()).isEqualTo(null);

        memberRepository.save(newMember);

        assertThat(newMember.getRoleType()).isEqualTo(USER);

        em.flush();
        em.clear();

        Optional<Member> findMember = memberRepository.findById(newMember.getId());
        assertThat(findMember).isNotNull();
    }

}