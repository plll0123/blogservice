package com.blog.blogservice.repository;

import com.blog.blogservice.domain.Member;
import com.blog.blogservice.repository.query.MemberRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    Optional<Member> findByLoginId(String loginId);
}
