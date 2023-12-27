package com.weavus.studyweb;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weavus.studyweb.entity.Studys;
import com.weavus.studyweb.service.StudysService;

@SpringBootTest
class StudywebApplicationTests {

    @Autowired
    private StudysService studys;

    @Test
    void contextLoads() {

    }

    @Test
    void saveStudys() throws ParseException{
        Studys study = new Studys();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String str = "2024/1/12";
        Date date = sdf.parse(str);

        Timestamp start = new Timestamp(date.getTime());

        study.setStudyName("test1");
        study.setStudyDetail("testestest");
        study.setCategory("study");
        study.setWriterName("tester");
        study.setStartDate(start);

        studys.createStudy(study);
    }
    @Test
    void findStudys(){
        System.out.println(studys.findAll());
    }

    @Test
    void findRecruiting(){
        System.out.println(studys.findRecruiting());
    }

}
