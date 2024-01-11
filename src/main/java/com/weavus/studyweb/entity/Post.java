package com.weavus.studyweb.entity;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String contents;
    @Column(name="view_count")
    private Integer viewCount;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private PostCategory category;
    
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @CreationTimestamp
    @Column(name="create_date")
    private Timestamp createDate;

    @OneToMany
    @JoinColumn(name="post_id")
    private List<Comment> comments;

    @OneToMany
    @JoinColumn(name="post_id")
    private List<PostLike> likes;
}
