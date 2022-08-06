package com.blog.blogservice.controller.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

import static com.blog.blogservice.processor.config.ViewNameConfig.LOGIN_FORM;

@Data
@NoArgsConstructor
public class LoginForm {

    @NotEmpty(message = "아이디를 입력해주세요.")
    private String loginId;
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;

    private boolean rememberId;
    private final String exOccursView = LOGIN_FORM;

    @Builder
    public LoginForm(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

}
