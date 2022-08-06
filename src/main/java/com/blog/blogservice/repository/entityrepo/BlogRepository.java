package com.blog.blogservice.repository.entityrepo;

import com.blog.blogservice.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
