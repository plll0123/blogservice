package com.blog.blogservice.controller;

import com.blog.blogservice.domain.Member;
import com.blog.blogservice.exception.IdOrPwMisMatch;
import com.blog.blogservice.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.HttpSession;

import java.util.Objects;

import static com.blog.blogservice.processor.annotation.LoginConst.MEMBER_ID;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional(readOnly = true)
@AutoConfigureMockMvc
@SpringBootTest
class MemberControllerTest {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    MockMvc mockMvc;
    @Autowired
    HttpSession session;
    @Autowired
    MemberRepository memberRepository;

    Member fixtureMember;
    MultiValueMap<String, String> fixtureMVMap;

    private Class<? extends Exception> getResolvedException(MvcResult result) {
        return Objects.requireNonNull(result.getResolvedException()).getClass();
    }

    @BeforeEach
    void initFixture() {
        fixtureMember = Member.builder()
                .loginId("test")
                .password("test")
                .name("test")
                .build();

        fixtureMVMap = new LinkedMultiValueMap<>();
        fixtureMVMap.add("loginId", "test");
        fixtureMVMap.add("password", "test");

    }

    @Test
    @DisplayName("[NEGATIVE] - 인증이 필요한 로직에 인증 없이 접근하면 home으로 이동되고 세션이 null이다.")
    void notLoginTest() throws Exception {
        //given
        Assertions.assertThatThrownBy(() ->
                mockMvc.perform(post("/board/register"))
                        .andExpect(request().sessionAttribute(MEMBER_ID, 1L))
                        .andDo(print()), "Ses123"
                )
                .isInstanceOf(AssertionError.class);
        //when

        //then
    }

    @Test
    @DisplayName("[NEGATIVE] - 존재하지 않는 id로 로그인 시도시 IdOrPwMisMatch Ex 발생. 홈으로 이동")
    void loginNegative1() throws Exception {
        //given

        MultiValueMap<String, String> form = fixtureMVMap;

        //expected
        mockMvc.perform(post("/login")
                        .contentType(APPLICATION_FORM_URLENCODED)
                        .params(form))
                .andExpect(view().name("welcome"))
                .andExpect(result -> getResolvedException(result).isAssignableFrom(IdOrPwMisMatch.class))
                .andDo(print());
    }

    @Test
    @DisplayName("[NEGATIVE] - 아이디는 일치하지만 비밀번호가 일지하지 않을 경우 IdOrPwMisMatch Ex 발생")
    void loginNegative2() throws Exception {

        memberRepository.save(fixtureMember);

        MultiValueMap<String, String> exceptionThrowMap = fixtureMVMap;
        exceptionThrowMap.clear();
        exceptionThrowMap.add("loginId", "test");
        exceptionThrowMap.add("password", "대놓고 틀린 패스워드");

        mockMvc.perform(post("/login")
                        .contentType(APPLICATION_FORM_URLENCODED)
                        .params(exceptionThrowMap)
                )
                .andExpect(view().name("welcome"))
                .andExpect(result -> getResolvedException(result).isAssignableFrom(IdOrPwMisMatch.class))
                .andDo(print());
    }

    @Test
    @Transactional
    @DisplayName("[SUCCESS] - 로그인 후 세션과 redirection 체크")
    void loginSuccess() throws Exception {

        //given
        Member saveMember = memberRepository.save(fixtureMember);

        mockMvc.perform(post("/login")
                        .contentType(APPLICATION_FORM_URLENCODED)
                        .params(fixtureMVMap)
                )
                .andExpect(request().sessionAttribute(MEMBER_ID, saveMember.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andDo(print());
    }

    @Test
    @Transactional
    @DisplayName("[SUCCESS] - 로그인 한 유저는 세션에 정보가 저장되어있다.")
    void loginMember_session_test() throws Exception {
        //given
        Member saveMember = memberRepository.save(fixtureMember);

        mockMvc.perform(post("/login")
                        .contentType(APPLICATION_FORM_URLENCODED)
                        .params(fixtureMVMap)
                )
                .andExpect(request().sessionAttribute(MEMBER_ID, saveMember.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andDo(print());

        mockMvc.perform(post("/blog")
                )
                .andExpect(status().isOk());
    }
}