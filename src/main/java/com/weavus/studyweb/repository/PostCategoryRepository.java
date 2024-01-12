package com.weavus.studyweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weavus.studyweb.entity.PostCategory;


@Repository
public interface PostCategoryRepository extends JpaRepository<PostCategory, Long> {
    
    PostCategory findByName(String name);
    PostCategory findByUrl(String url);
}
