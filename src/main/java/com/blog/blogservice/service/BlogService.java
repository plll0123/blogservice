package com.blog.blogservice.service;

import com.blog.blogservice.repository.BlogRepository;
import com.blog.blogservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BlogService {

    private final MemberRepository memberRepository;
    private final BlogRepository blogRepository;

//    public Long create(BlogCreate blogCreate){
//
//    }

}
