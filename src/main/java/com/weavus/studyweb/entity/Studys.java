package com.weavus.studyweb.entity;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String studyName;
    private String category;
    private String studyDetail;
    private String writerName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @CreationTimestamp
    private Timestamp createDate;
    private Timestamp updateDate;
}
