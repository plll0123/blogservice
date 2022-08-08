package com.blog.blogservice.processor.interceptor.session;

import com.blog.blogservice.domain.Member;
import com.blog.blogservice.processor.annotation.RequiredLogin;
import com.blog.blogservice.service.InterceptorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.blog.blogservice.processor.config.LoginConstConfig.LOGGED_IN;
import static com.blog.blogservice.processor.config.LoginConstConfig.MEMBER_ID;

@Component
@RequiredArgsConstructor
public class ChainNo2 implements SessionCheckChain {

    private final InterceptorService sessionService;

    @Override
    public boolean sessionCheck(HttpServletRequest request) {
        Long memberId = (Long) request.getAttribute(MEMBER_ID);

        Member loginMember = sessionService.getLoginMember(memberId);

        request.setAttribute(LOGGED_IN, loginMember);

        return true;
    }
}