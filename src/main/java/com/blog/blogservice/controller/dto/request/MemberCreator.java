package com.blog.blogservice.controller.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberCreator {

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;

    @Builder
    public MemberCreator(String loginId, String password, String name) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }
}
