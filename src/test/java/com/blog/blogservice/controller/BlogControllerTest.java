package com.blog.blogservice.controller;

import com.blog.blogservice.domain.Member;
import com.blog.blogservice.fixture.BlogCreateFixture;
import com.blog.blogservice.processor.interceptor.session.SessionCheckChain;
import com.blog.blogservice.repository.entityrepo.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.blog.blogservice.fixture.BlogCreateFixture.blogDtoFixture;
import static com.blog.blogservice.fixture.LoginFormFixture.loginFormFixture;
import static com.blog.blogservice.fixture.MemberFixture.memberFixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = RANDOM_PORT)
class BlogControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    MockMvc mockMvc;

    @LocalServerPort
    private int port;

    @Test
    @DisplayName("블로그 생성 테스트")
    void create_blog() throws Exception {

        memberRepository.save(memberFixture());
        MultiValueMap<String, String> loginFormMap = new LinkedMultiValueMap<>();
        loginFormMap.add("loginId", "test");
        loginFormMap.add("password", "test");
        MvcResult mvcResult = mockMvc.perform(post("/login")
                .contentType(APPLICATION_FORM_URLENCODED)
                .params(loginFormFixture())
        ).andReturn();

        HandlerInterceptor[] interceptors = mvcResult.getInterceptors();
        for (HandlerInterceptor interceptor : interceptors) {
            System.out.println("interceptor = " + interceptor);
        }

        RequestEntity<Void> reqEntity = RequestEntity
                .post("http://localhost:" + port + "/login", loginFormMap)
                .contentType(APPLICATION_FORM_URLENCODED)
                .build();

        ResponseEntity<String> stringResponseEntity =
                testRestTemplate.exchange(reqEntity, String.class);

        System.out.println("stringResponseEntity.toString() = " + stringResponseEntity.toString());
//                testRestTemplate.postForEntity("http://localhost:" + port + "/login", loginFormMap, String.class);
        HttpHeaders headers = stringResponseEntity.getHeaders();
        for (String s : headers.keySet()) {
            System.out.println("headers.keySet() = " + s);
        }
        List<String> cookies = headers.get("Set-Cookie");
        assert cookies != null;
        String cookie = cookies.get(0);
        String[] cookiePair = cookie.split(";")[0]
                .split("=");

        Cookie reqCookie = new Cookie(cookiePair[0], cookiePair[1]);


        ResultActions perform = mockMvc.perform(post("http://localhost:" + port + "/blog")
                .contentType(APPLICATION_FORM_URLENCODED)
                .params(blogDtoFixture())
                .cookie(reqCookie)
        );
        perform
                .andExpect(cookie().exists("JSESSIONID"))
                .andDo(print());

        long count = Arrays.stream(Objects.requireNonNull(perform.andReturn()
                        .getInterceptors()))
                .filter(f -> SessionCheckChain.class.isAssignableFrom(f.getClass()))
                .count();

        assertThat(count).isEqualTo(2);
    }

    @Test
    @DisplayName("쿠키 테스트(콘솔)")
    void test() {
        //given
        memberRepository.save(memberFixture());
        MultiValueMap<String, String> loginFormMap = new LinkedMultiValueMap<>();
        loginFormMap.add("loginId", "test");
        loginFormMap.add("password", "test");
        RequestEntity<MultiValueMap<String, String>> reqEntity = RequestEntity
                .post("http://localhost:" + port + "/login")
                .contentType(APPLICATION_FORM_URLENCODED)
                .body(loginFormMap);

        Assertions.assertThrows(NullPointerException.class, () -> {
            HttpHeaders headers2 = reqEntity.getHeaders();
            List<String> cookies123 = headers2.get("Set-Cookie");
            String cookie123 = cookies123.get(0);
            String[] cookiePair123 = cookie123.split(";")[0]
                    .split("=");

            for (String s : cookiePair123) {
                System.out.println(" sresponse " + s);
            }
        });

        ResponseEntity<String> stringResponseEntity =
                testRestTemplate.exchange(reqEntity, String.class);

        HttpHeaders headers = stringResponseEntity.getHeaders();
        List<String> cookies = headers.get("Set-Cookie");
        String cookie = cookies.get(0);
        String[] cookiePair = cookie.split(";")[0]
                .split("=");

        for (String s : cookiePair) {
            System.out.println(" sresponse " + s);
        }

        Cookie reqCookie = new Cookie(cookiePair[0], cookiePair[1]);

        RequestEntity<MultiValueMap<String, String>> creatBlogEntity = RequestEntity
                .post("http://localhost:" + port + "/login")
                .contentType(APPLICATION_FORM_URLENCODED)
                .body(BlogCreateFixture.blogDtoFixture());

        HttpHeaders headers1 = creatBlogEntity.getHeaders();
        List<String> cookies1 = headers1.get("Set-Cookie");
        String cookie1 = cookies.get(0);
        String[] cookiePair1 = cookie.split(";")[0]
                .split("=");

        for (String s : cookiePair1) {
            System.out.println("srequest = " + s);
        }
    }

    @Test
    @DisplayName("유저 검증 테스트")
    void validation_user() {

        Member fixtureMember = memberRepository.save(memberFixture());

        RequestEntity<MultiValueMap<String, String>> loginEntity = RequestEntity
                .post("http://localhost:" + port + "/login")
                .contentType(APPLICATION_FORM_URLENCODED)
                .body(loginFormFixture());

        ResponseEntity<String> receivedResponse  = testRestTemplate.exchange(loginEntity, String.class);


        RequestEntity<MultiValueMap<String, String>> blogCreateEntity = RequestEntity
                .post("http://localhost:" + port + "/blog")
                .contentType(APPLICATION_FORM_URLENCODED)
                .body(blogDtoFixture());

        ResponseEntity<String> receivedBlogResponse = testRestTemplate.exchange(blogCreateEntity, String.class);

        Cookie blogResponseCookie = findCookie(receivedBlogResponse.getHeaders());
        assertThat(blogResponseCookie.getName()).isEqualTo("JSESSIONID");
    }

    private Cookie findCookie(HttpHeaders headers){
        List<String> cookies = headers.get("Set-Cookie");
        String cookieString = cookies.get(0);
        String[] cookiePair = cookieString.split(";")[0]
                .split("=");

        return new Cookie(cookiePair[0], cookiePair[1]);
    }
}

