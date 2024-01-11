package com.weavus.studyweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weavus.studyweb.entity.PostLike;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long>{

    int countByPost_Id(Long postId);
}
