package com.weavus.studyweb.dto;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudysDTO {
    
    private String category;
    private String studyDetail;
    private String startDate;
    private String endDate;

    public Timestamp getStartDate() {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(this.startDate);
            return new java.sql.Timestamp(date.getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Timestamp getEndDate() {
        try {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(this.endDate);
            return new java.sql.Timestamp(date.getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
