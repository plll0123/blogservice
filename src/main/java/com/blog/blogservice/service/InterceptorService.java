package com.blog.blogservice.service;

import com.blog.blogservice.domain.Blog;
import com.blog.blogservice.domain.Member;
import com.blog.blogservice.domain.RoleType;
import com.blog.blogservice.exception.NonUniqueBlogException;
import com.blog.blogservice.processor.interceptor.UserDetails;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import static com.blog.blogservice.domain.RoleType.OWNER;
import static com.blog.blogservice.processor.config.LoginConstConfig.LOGGED_IN;
import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

@Service
@RequiredArgsConstructor
public class InterceptorService {

    private final MemberService memberService;
    private final BlogService blogService;

    public void isValidMember() {
        System.out.println("InterceptorService.isValidId");
        Long memberId = ((UserDetails) sessionOfCurrentRequest().getAttribute(LOGGED_IN))
                .getMemberId();
        if(memberService.findById(memberId) == null){
            logout();
            throw new UnAuthenticatedAccessException();
        }
    }

    public boolean isValidBlog(Long blogId){
        return blogService.find(blogId) == null;
    }

    public void login(Member member) {
        sessionOfCurrentRequest()
                .setAttribute(LOGGED_IN, toUserDetails(member));
    }

    public Object getLoginMember(){
        return sessionOfCurrentRequest()
                .getAttribute(LOGGED_IN);

    }

    public void logout() {
        System.out.println("InterceptorService.logout");
        sessionOfCurrentRequest()
                .invalidate();
    }


    private HttpSession sessionOfCurrentRequest() {
        return ((ServletRequestAttributes) currentRequestAttributes()).getRequest().getSession();
    }

    private UserDetails toUserDetails(Member loginMember) {
        UserDetails userDetails = new UserDetails(loginMember);
        System.out.println("userDetails = " + userDetails);
        return userDetails;
    }

    public void userHasBlog() {
        if(((UserDetails)sessionOfCurrentRequest().getAttribute(LOGGED_IN))
                .getRole() == OWNER){
            throw new NonUniqueBlogException();
        }
    }
}
