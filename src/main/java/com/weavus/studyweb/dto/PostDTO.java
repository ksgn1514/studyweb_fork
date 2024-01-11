package com.weavus.studyweb.dto;

import com.weavus.studyweb.entity.Post;

import java.text.SimpleDateFormat;

import lombok.Data;

@Data
public class PostDTO {

    private Long id;
    private String title;
    private String authorName;
    private int commentCount;
    private int likeCount;
    private String categoryName;
    private String createDate;
    private String contents;
    private int viewCount;

    public static PostDTO toPostDTO(Post post) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        PostDTO postDTO = new PostDTO();

        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setAuthorName(post.getAuthor().getUsername());
        postDTO.setCommentCount(post.getComments().size());
        postDTO.setLikeCount(post.getLikes().size());
        postDTO.setCategoryName(post.getCategory().getName());
        postDTO.setCreateDate(dateFormat.format(post.getCreateDate()));
        postDTO.setContents(post.getContents());
        postDTO.setViewCount(post.getViewCount());

        return postDTO;
    }

}
