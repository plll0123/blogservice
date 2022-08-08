package com.blog.blogservice.fixture;

import com.blog.blogservice.controller.dto.request.BlogCreate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class BlogCreateFixture {

    public static MultiValueMap<String, String> blogDtoFixture(){
        LinkedMultiValueMap<String, String> stringStringLinkedMultiValueMap = new LinkedMultiValueMap<>();
        stringStringLinkedMultiValueMap.add("title", "test");
        stringStringLinkedMultiValueMap.add("tag", "test");
        return stringStringLinkedMultiValueMap;
    }
}
