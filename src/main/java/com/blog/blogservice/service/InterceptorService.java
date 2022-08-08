package com.blog.blogservice.service;

import com.blog.blogservice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.blog.blogservice.processor.config.LoginConstConfig.MEMBER_ID;
import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

@Service
@RequiredArgsConstructor
public class InterceptorService {

    private final MemberService memberService;

    public Member getLoginMember(Long memberId) {
        return memberService.findById(memberId);
    }

    public void login(Long loginId) {
        sessionOfCurrentRequest()
                .setAttribute(MEMBER_ID, loginId);
    }

    public void logout() {
        sessionOfCurrentRequest()
                .invalidate();
    }

    private HttpSession sessionOfCurrentRequest() {
        ServletRequestAttributes requestAttr = (ServletRequestAttributes) currentRequestAttributes();
        return requestAttr.getRequest().getSession();
    }
}
