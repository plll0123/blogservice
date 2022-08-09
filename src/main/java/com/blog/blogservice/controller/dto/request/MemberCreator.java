package com.blog.blogservice.controller.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MemberCreator {

    private String loginId;
    private String password;
    private String name;

    @Builder
    public MemberCreator(String loginId, String password, String name) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }
}
