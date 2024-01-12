package com.weavus.studyweb.dto;

import lombok.Data;

@Data
public class PostCreateDTO {

    private String title;
    private String contents;
    private String authorUsername;
    private String categoryUrl;
    private String filepath;
    
}
