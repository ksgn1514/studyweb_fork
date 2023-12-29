package com.weavus.studyweb.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.weavus.studyweb.entity.StudyApplication;
import com.weavus.studyweb.entity.Studys;
import com.weavus.studyweb.service.StudyApplicationService;

@Component
public class CommonUtility {
    
    @Autowired
    private StudyApplicationService studyApplicationService;

    public String nl2br(String text) {
        return text.replace("\n", "<br>");
    }

    public List<StudysDetail> toDetailList(List<Studys> studysList) {
        List<StudysDetail> studysDetailList = new ArrayList<>();
        for (Studys studys : studysList) {
            studys.setStudyDetail(nl2br(studys.getStudyDetail()));
            List<StudyApplication> applications = studyApplicationService.findByStudy(studys);
            int applicantCount = applications.size();

            StudysDetail studysDetail = new StudysDetail();
            studysDetail.setStudys(studys);
            studysDetail.setApplications(applications);
            studysDetail.setApplicantCount(applicantCount);
            studysDetailList.add(studysDetail);
        }

        return studysDetailList;
    }
}
