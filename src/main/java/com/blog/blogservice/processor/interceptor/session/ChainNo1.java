package com.blog.blogservice.processor.interceptor.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.blog.blogservice.processor.config.LoginConstConfig.MEMBER_ID;

@Component
public class ChainNo1 implements SessionCheckChain {

    @Override
    public boolean sessionCheck(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session == null || getSessionAttr(session) == null) {
            return false;
        }

        request.setAttribute(MEMBER_ID, getSessionAttr(session));
        return true;
    }

    private Object getSessionAttr(HttpSession session) {
        return session.getAttribute(MEMBER_ID);
    }
}
