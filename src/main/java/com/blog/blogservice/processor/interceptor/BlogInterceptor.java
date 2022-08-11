package com.blog.blogservice.processor.interceptor;

import com.blog.blogservice.service.InterceptorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class BlogInterceptor implements HandlerInterceptor {

    private final InterceptorService interceptorService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(handler instanceof HandlerMethod && (((HandlerMethod) handler).getMethod().getName().equals("register"))){
            interceptorService.userHasBlog();
        }
        return true;
    }
}
