package com.weavus.studyweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weavus.studyweb.entity.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    
    List<Post> findAllByOrderByCreateDateDesc();
    List<Post> findByCategory_Id(Long categoryId);
}
