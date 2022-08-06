package com.blog.blogservice.processor.interceptor.session;

import com.blog.blogservice.processor.annotation.RequiredLogin;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SessionCheckChain extends HandlerInterceptor {

    @Override
    default boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (targetHandlerHasRequiredLoginAnnotation(handler)) {
            return sessionCheck(request);
        }
        return true;
    }

    default boolean targetHandlerHasRequiredLoginAnnotation(Object handler) {
        return (handler instanceof HandlerMethod && ((HandlerMethod) handler).hasMethodAnnotation(RequiredLogin.class));
    }

    boolean sessionCheck(HttpServletRequest request);


}
