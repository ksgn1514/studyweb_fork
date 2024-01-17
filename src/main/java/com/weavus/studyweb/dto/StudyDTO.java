package com.weavus.studyweb.dto;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.weavus.studyweb.entity.Study;

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
public class StudyDTO {

    private Long id;
    private String studyName;
    private String category;
    private String studyDetail;
    private String writerUserid;
    private String filepath;
    private String startDate;
    private String endDate;
    
    private String status;
    private String createDate;


    public Timestamp getTsStartDate() {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(this.startDate);
            return new java.sql.Timestamp(date.getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Timestamp getTsEndDate() {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(this.endDate);
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void setStatus(){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Timestamp dstartDate = this.getTsStartDate();
        Timestamp dendDate = this.getTsEndDate();

        if(dstartDate.getTime() > now.getTime()){
            this.status = "모집중";
        }
        else if (dstartDate.getTime() < now.getTime() && dendDate.getTime() > now.getTime()) {
            this.status = "진행중";
        } else {
        this.status = "완료";
        }
    }

    public static StudyDTO toStudyDTO (Study study){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        StudyDTO studyDTO = new StudyDTO();
        studyDTO.setId(study.getId());
        studyDTO.setStudyName(study.getStudyName());
        studyDTO.setCategory(study.getCategory());
        studyDTO.setWriterUserid(study.getWriterUserid());
        studyDTO.setFilepath(study.getFilepath());
        studyDTO.setStudyDetail(study.getStudyDetail());
        
        String start = dateFormat.format(study.getStartDate());
        String end = dateFormat.format(study.getEndDate());

        studyDTO.setStartDate(start);
        studyDTO.setEndDate(end);

        studyDTO.setStatus();
        studyDTO.setCreateDate(dateFormat.format(study.getCreateDate()));

        return studyDTO;
    }
}
