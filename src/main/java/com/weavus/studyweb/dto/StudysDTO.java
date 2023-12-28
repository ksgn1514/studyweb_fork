package com.weavus.studyweb.dto;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.weavus.studyweb.entity.Studys;

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

    private long id;
    private String studyName;
    private String category;
    private String studyDetail;
    private String writerUserid;
    private String filepath;
    private String startDate;
    private String endDate;
    
    private String status;


    public Timestamp getStartDate() {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(this.startDate);
            return new java.sql.Timestamp(date.getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Timestamp getEndDate() {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(this.endDate);
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void setStatus(){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Timestamp dstartDate = this.getStartDate();
        Timestamp dendDate = this.getEndDate();

        if(dstartDate.getTime() > now.getTime()){
            this.status = "모집중";
        }
        else if (dstartDate.getTime() < now.getTime() && dendDate.getTime() > now.getTime()) {
            this.status = "진행중";
        } else {
        this.status = "완료";
        }
    }

    public static StudysDTO toStudysDTO (Studys studys){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        StudysDTO studyDTO = new StudysDTO();
        studyDTO.setId(studys.getId());
        studyDTO.setStudyName(studys.getStudyName());
        studyDTO.setCategory(studys.getCategory());
        studyDTO.setWriterUserid(studys.getWriterUserid());
        studyDTO.setFilepath(studys.getFilepath());
        studyDTO.setStudyDetail(studys.getStudyDetail());
        
        Date start = studys.getStartDate();
        Date end = studys.getEndDate();
        studyDTO.setStartDate(dateFormat.format(start));
        studyDTO.setEndDate(dateFormat.format(end));

        studyDTO.setStatus();

        return studyDTO;
    }
}
