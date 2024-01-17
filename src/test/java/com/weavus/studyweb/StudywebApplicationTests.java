package com.weavus.studyweb;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weavus.studyweb.entity.Study;
import com.weavus.studyweb.service.StudyService;

@SpringBootTest
class StudywebApplicationTests {

    @Autowired
    private StudyService studyService;

    @Test
    void contextLoads() {

    }

    @Test
    void saveStudy() throws ParseException{
        Study study = new Study();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String str = "2024/1/12";
        Date date = sdf.parse(str);

        Timestamp start = new Timestamp(date.getTime());

        study.setStudyName("test1");
        study.setStudyDetail("testestest");
        study.setCategory("study");
        study.setWriterUserid("123123");
        study.setStartDate(start);

        studyService.createStudy(study);
    }
    @Test
    void findStudy(){
        System.out.println(studyService.findAll());
    }

    @Test
    void findRecruiting(){
        System.out.println(studyService.findRecruiting());
    }

}
