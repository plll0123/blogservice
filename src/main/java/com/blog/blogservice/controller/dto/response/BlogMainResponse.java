package com.blog.blogservice.controller.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BlogMainResponse {

    private Long blogId;
    private String title;
    private String tag;
    private List<PostResponse> posts;

    @QueryProjection
    public BlogMainResponse(Long blogId, String title, String tag){
        this.blogId = blogId;
        this.title = title;
        this.tag = tag;
    }
}
