package com.blog.blogservice.fixture;

import com.blog.blogservice.controller.dto.request.LoginForm;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class LoginFormFixture {

    public static MultiValueMap<String, String> loginFormFixture() {
        MultiValueMap<String, String> loginForm = new LinkedMultiValueMap<>();
        loginForm.add("loginId", "test");
        loginForm.add("password", "test");

        return loginForm;
    }
}
