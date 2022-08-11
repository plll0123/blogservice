package com.blog.blogservice.processor.config;

import com.blog.blogservice.processor.argumentResolver.MemberArgumentResolver;
import com.blog.blogservice.processor.interceptor.BlogInterceptor;
import com.blog.blogservice.processor.interceptor.LoginInterceptor;
import com.blog.blogservice.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final MemberArgumentResolver memberResolver;
    private final LoginInterceptor loginInterceptor;
    private final BlogInterceptor blogInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).order(1);
        registry.addInterceptor(blogInterceptor).order(2);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(memberResolver);
    }
}
