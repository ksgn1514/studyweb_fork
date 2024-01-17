package com.weavus.studyweb.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.weavus.studyweb.dto.PostDetailDTO;
import com.weavus.studyweb.dto.StudyDTO;
import com.weavus.studyweb.dto.StudyDetail;
import com.weavus.studyweb.entity.Post;
import com.weavus.studyweb.entity.StudyApplication;
import com.weavus.studyweb.entity.Study;
import com.weavus.studyweb.service.StudyApplicationService;
import com.weavus.studyweb.service.StudyService;

@Component
public class CommonUtility {
    
    @Autowired
    private StudyApplicationService studyApplicationService;

    @Autowired
    private StudyService studyService;

    public String nl2br(String text) {
        return text.replace("\n", "<br>");
    }

    public List<StudyDetail> toDetailList(List<Study> studyList) {
        List<StudyDetail> studyDetailList = new ArrayList<>();
        for (Study study : studyList) {
            study.setStudyDetail(nl2br(study.getStudyDetail()));
            List<StudyApplication> applications = studyApplicationService.findByStudy(study);
            int applicantCount = applications.size();

            StudyDetail studyDetail = new StudyDetail();
            studyDetail.setStudy(StudyDTO.toStudyDTO(study));
            studyDetail.setApplications(applications);
            studyDetail.setApplicantCount(applicantCount);
            studyDetailList.add(studyDetail);
        }

        return studyDetailList;
    }

    //Study전체 리스트 취득
    public List<StudyDetail> getStudyListAll() {
        List<Study> studyList = studyService.findAll();
        List<StudyDetail> studyDetails = toDetailList(studyList);
        return studyDetails;
    }

    //Study 모집중 리스트 취득
    public List<StudyDetail> getStudyListRecruiting() {
        List<Study> studyList = studyService.findRecruiting();
        List<StudyDetail> studyDetails = toDetailList(studyList);
        return studyDetails;
    }

    public List<PostDetailDTO> toPostDetailDto (List<Post> postList) {
        List<PostDetailDTO> postDetailDtoList = new ArrayList<>();
        for (Post post : postList) {
            PostDetailDTO postDetailDto = PostDetailDTO.toPostDetailDTO(post);
            postDetailDtoList.add(postDetailDto);
        }
        return postDetailDtoList;
    }

}
