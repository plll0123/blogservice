package com.blog.blogservice.controller.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostResponse {

    private String title;
    private String content;
    private LocalDateTime latest;

    @QueryProjection
    public PostResponse(String title, String content, LocalDateTime latest) {
        this.title = title;
        this.content = content;
        this.latest = latest;
    }
}
