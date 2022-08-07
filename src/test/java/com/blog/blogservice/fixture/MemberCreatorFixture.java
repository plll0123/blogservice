package com.blog.blogservice.fixture;

import com.blog.blogservice.controller.dto.request.MemberCreator;
import lombok.Data;

@Data
public class MemberCreatorFixture {

    public static MemberCreator memberCreatorFixture(){
        return MemberCreator.builder()
                .loginId("test")
                .password("test")
                .name("test")
                .build();
    }
}
