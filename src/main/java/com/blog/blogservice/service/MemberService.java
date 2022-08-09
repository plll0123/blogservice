package com.blog.blogservice.service;

import com.blog.blogservice.controller.dto.request.MemberCreator;
import com.blog.blogservice.controller.dto.request.LoginForm;
import com.blog.blogservice.domain.Member;
import com.blog.blogservice.exception.IdOrPwMisMatch;
import com.blog.blogservice.exception.MemberNotFoundException;
import com.blog.blogservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(MemberCreator memberCreate) {
        return memberRepository.save(createMember(memberCreate))
                .getId();
    }

    public Member findMember(LoginForm form) {
        Member target = memberRepository.findByLoginId(form.getLoginId())
                .orElseThrow(IdOrPwMisMatch::new);

        target.validationPwd(form.getPassword());

        return target;
    }

    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(MemberNotFoundException::new);
    }

    public Long findIdByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId)
                .orElseThrow(MemberNotFoundException::new)
                .getId();
    }

    private Member createMember(MemberCreator memberCreate) {
        return Member.builder()
                .name(memberCreate.getName())
                .loginId(memberCreate.getLoginId())
                .password(memberCreate.getPassword())
                .build();
    }

}
