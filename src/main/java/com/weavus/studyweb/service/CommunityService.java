package com.weavus.studyweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weavus.studyweb.entity.Post;
import com.weavus.studyweb.entity.PostCategory;
import com.weavus.studyweb.repository.PostCategoryRepository;
import com.weavus.studyweb.repository.PostLikeRepository;
import com.weavus.studyweb.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommunityService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostLikeRepository postLikeRepository;
    @Autowired
    private PostCategoryRepository postCategoryRepository;

    public List<Post> findAllPost(){
        return postRepository.findAll();
    }

    public List<PostCategory> findAllPostCategory(){
        return postCategoryRepository.findAll();
    }

    public List<Post> findPostByCategory(String categoryUrl){
        PostCategory category = postCategoryRepository.findByUrl(categoryUrl);
        Long categoryId = category.getId();
        return postRepository.findByCategory_Id(categoryId);
    }

    public int countLike(Long postId) {
        return postLikeRepository.countByPost_Id(postId);
    }

}
