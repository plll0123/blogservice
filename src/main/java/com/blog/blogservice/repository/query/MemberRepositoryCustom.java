package com.blog.blogservice.repository.query;

import com.blog.blogservice.domain.Member;

import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<Member> findByName(String name);
}
