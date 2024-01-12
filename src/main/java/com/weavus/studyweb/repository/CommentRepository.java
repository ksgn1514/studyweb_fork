package com.weavus.studyweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weavus.studyweb.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
}
