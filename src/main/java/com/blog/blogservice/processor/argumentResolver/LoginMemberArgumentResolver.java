package com.blog.blogservice.processor.argumentResolver;

import com.blog.blogservice.processor.annotation.Login;
import com.blog.blogservice.domain.Member;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

import static com.blog.blogservice.processor.annotation.LoginConst.LOGGED_IN;

@Component
public interface LoginMemberArgumentResolver extends HandlerMethodArgumentResolver {

    @Override
    default boolean supportsParameter(MethodParameter parameter) {

        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        boolean isMemberType = Member.class.isAssignableFrom(parameter.getParameterType());

        return hasLoginAnnotation && isMemberType;
    }

    @Override
    default Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        return request.getAttribute(LOGGED_IN);
    }

    Object extractMemberFromSession(HttpServletRequest request);
}
