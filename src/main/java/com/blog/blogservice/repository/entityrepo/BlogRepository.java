package com.blog.blogservice.repository.entityrepo;

import com.blog.blogservice.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Query("select b from Blog b join fetch b.member where b.id = :id")
    Optional<Blog> findForWritePost(@Param("id") Long id);
}
