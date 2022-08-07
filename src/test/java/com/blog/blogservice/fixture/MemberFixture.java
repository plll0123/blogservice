package com.blog.blogservice.fixture;

import com.blog.blogservice.domain.Member;

public class MemberFixture {

    public static Member memberFixture(){
        return Member.builder()
                .loginId("test")
                .password("test")
                .name("test")
                .build();
    }

}
