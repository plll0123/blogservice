package com.blog.blogservice.repository;

import com.blog.blogservice.domain.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.blog.blogservice.domain.QMember.*;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory qFactory;

    @Override
    public Optional<Member> findByName123(String name) {
        return Optional.ofNullable(
                qFactory
                        .selectFrom(member)
                        .where(member.name.eq(name))
                        .fetchOne()
        );
    }
}
