package com.blog.blogservice.processor.argumentResolver;

import javax.servlet.http.HttpServletRequest;

import static com.blog.blogservice.processor.annotation.LoginConst.LOGGED_IN;

public class MemberArgumentResolver implements LoginMemberArgumentResolver {

    @Override
    public Object extractMemberFromSession(HttpServletRequest request) {
        return request.getAttribute(LOGGED_IN);
    }
}
