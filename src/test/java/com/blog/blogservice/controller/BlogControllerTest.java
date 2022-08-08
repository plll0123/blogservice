package com.blog.blogservice.controller;

import com.blog.blogservice.domain.Member;
import com.blog.blogservice.processor.config.LoginConstConfig;
import com.blog.blogservice.repository.MemberRepository;
import com.blog.blogservice.repository.entityrepo.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.blog.blogservice.fixture.MemberFixture.memberFixture;
import static com.blog.blogservice.processor.config.LoginConstConfig.MEMBER_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = RANDOM_PORT)
class BlogControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MockMvc mockMvc;
    @LocalServerPort
    private int port;

    Cookie loginCookie;
    MockHttpSession session = new MockHttpSession();

    @BeforeEach
    void initFixture() {
//        Member savedMember = memberRepository.save(memberFixture());
//
//        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
//        parameters.add("loginId", savedMember.getLoginId());
//        parameters.add("password", savedMember.getPassword());
//
//        ResponseEntity<String> response = testRestTemplate
//                .postForEntity("/login", parameters, String.class);
//        HttpHeaders headers = response.getHeaders();
//        List<String> cookies = headers.get("Set-Cookie");
//        String[] cookie = cookies.get(0)
//                .split(";")[0]
//                .split("=");
//
//        loginCookie = new Cookie(cookie[0], cookie[1]);

    }

    @Test
    @DisplayName("testRestTemplate_teat@@")
    void testetse() {
        //given

        Member savedMember = memberRepository.save(memberFixture());

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("loginId", savedMember.getLoginId());
        parameters.add("password", savedMember.getPassword());

        ResponseEntity<String> response = testRestTemplate
                .postForEntity("/login", parameters, String.class);

        HttpHeaders headers = response.getHeaders();
        List<String> cookies = headers.get("Set-Cookie");
        String[] cookie = cookies.get(0)
                .split(";")[0]
                .split("=");

        System.out.println("cookie.key is = " + cookie[0]);
        System.out.println("cookie.value is = " + cookie[1]);

        HttpHeaders headers1 = response.getHeaders();
        for (String s : headers1.keySet()) {
            System.out.println(" header by ResponseEntity =  " + s);
        }
    }

    @Test
    @DisplayName("블로그 생성 테스트")
    void create_blog() throws Exception {
        Member savedMember = memberRepository.save(memberFixture());

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("loginId", savedMember.getLoginId());
        parameters.add("password", savedMember.getPassword());

        ResponseEntity<String> response = testRestTemplate
                .postForEntity("/login", parameters, String.class);

//        HttpHeaders headers = response.getHeaders();
//        List<String> cookies = headers.get("Set-Cookie");
//        String[] cookie = cookies.get(0)
//                .split(";")[0]
//                .split("=");

//        response.getHeaders().getRe
        MultiValueMap<String, String> blogDtoMap = new LinkedMultiValueMap<>();
        blogDtoMap.add("title", "test");
        blogDtoMap.add("tag", "test");

//        MockHttpSession session = new MockHttpSession();
//        session.setAttribute(MEMBER_ID, savedMember.getId());

        Map<String, Object> sessionAttrs = new HashMap<>();
        sessionAttrs.put(MEMBER_ID, savedMember.getId());

        mockMvc.perform(post("/blog")
                        .contentType(APPLICATION_FORM_URLENCODED)
                        .params(blogDtoMap)
                        .sessionAttrs(sessionAttrs)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(cookie().exists("JSESSIONID"))
                .andDo(print());
    }

    @Test
    @DisplayName("블로그 생성 테스스트트트트")
    void createcreate() throws Exception {
        Member savedMember = memberRepository.save(memberFixture());

        assertThat(savedMember.getId()).isEqualTo(1L);
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("loginId", savedMember.getLoginId());
        parameters.add("password", savedMember.getPassword());

        MvcResult mvcResult = mockMvc.perform(post("/login")
                .contentType(APPLICATION_FORM_URLENCODED)
                .params(parameters)
        )
                .andDo(print())
                .andReturn();


        MultiValueMap<String, String> blogDtoMap = new LinkedMultiValueMap<>();
        blogDtoMap.add("title", "test");
        blogDtoMap.add("tag", "test");
        MockHttpSession session = (MockHttpSession)mvcResult.getRequest().getSession();

        assertThat(session).isNotNull();
        System.out.println("session.getAttribute(\"memberId\") = " + session.getAttribute(MEMBER_ID));
        mockMvc.perform(post("/blog")
                        .contentType(APPLICATION_FORM_URLENCODED)
                        .params(blogDtoMap)
                        .session(session)
                )
                .andExpect(view().name("welcome"))
                .andExpect(request().sessionAttribute(MEMBER_ID, savedMember.getId()))
                .andDo(print());

    }
}

