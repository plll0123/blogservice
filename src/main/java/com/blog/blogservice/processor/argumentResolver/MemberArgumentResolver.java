package com.blog.blogservice.processor.argumentResolver;

import com.blog.blogservice.processor.annotation.Login;
import com.blog.blogservice.domain.Member;
import com.blog.blogservice.processor.interceptor.UserDetails;
import com.blog.blogservice.service.InterceptorService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

import static com.blog.blogservice.processor.config.LoginConstConfig.LOGGED_IN;

@Component
@RequiredArgsConstructor
public class MemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final InterceptorService interceptorService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        boolean isMemberType = UserDetails.class.isAssignableFrom(parameter.getParameterType());

        return hasLoginAnnotation && isMemberType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        return interceptorService.getLoginMember();
    }

}
