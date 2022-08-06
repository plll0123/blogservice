package com.blog.blogservice.controller.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Data
@NoArgsConstructor
public class LoginForm {

    private String loginId;
    private String password;
    private boolean rememberId;

    @Builder
    public LoginForm(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

}
