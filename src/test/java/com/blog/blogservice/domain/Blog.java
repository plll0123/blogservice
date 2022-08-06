package com.blog.blogservice.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

public class Blog {

    @Test
    @DisplayName("Blog 생성")
    void new_blog() {
        //given
        Member member = Member.builder()
                .loginId("test")
                .password("test")
                .name("tester")
                .build();

    }
}
