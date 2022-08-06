package com.blog.blogservice.processor.config;

import com.blog.blogservice.processor.interceptor.session.ChainNo1;
import com.blog.blogservice.processor.interceptor.session.ChainNo2;
import com.blog.blogservice.processor.interceptor.session.SessionCheckChain;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final SessionCheckChain chainNo1;
    private final SessionCheckChain chainNo2;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(chainNo1)
                .order(1);
        registry.addInterceptor(chainNo2)
                .order(2);
    }
}
