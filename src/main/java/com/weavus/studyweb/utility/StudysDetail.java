package com.weavus.studyweb.utility;

import java.util.List;

import com.weavus.studyweb.entity.StudyApplication;
import com.weavus.studyweb.entity.Studys;

import lombok.Data;

@Data
public class StudysDetail {
    
    private Studys studys;
    private List<StudyApplication> applications;
    private int applicantCount;

}
