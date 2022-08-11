package com.blog.blogservice.processor.interceptor;

import com.blog.blogservice.processor.annotation.RequiredLogin;
import com.blog.blogservice.service.InterceptorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.blog.blogservice.processor.config.LoginConstConfig.LOGGED_IN;

@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private final InterceptorService interceptorService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod && ((HandlerMethod) handler).hasMethodAnnotation(RequiredLogin.class)) {
            HttpSession session = request.getSession(false);

            if (session == null || session.getAttribute(LOGGED_IN) == null) {
                response.sendRedirect("/system/login");
                return false;
            }

            interceptorService.isValidMember();
        }
        return true;
    }
}
