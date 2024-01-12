package com.weavus.studyweb.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Studys {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studyName;
    private String category;
    private String studyDetail;
    private String writerUserid;
    private String filepath;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp endDate;

    @CreationTimestamp
    private Timestamp createDate;
    @UpdateTimestamp
    private Timestamp updateDate;
}
