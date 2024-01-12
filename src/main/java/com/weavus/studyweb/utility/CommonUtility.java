package com.weavus.studyweb.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.weavus.studyweb.dto.PostDetailDTO;
import com.weavus.studyweb.dto.StudysDTO;
import com.weavus.studyweb.dto.StudysDetail;
import com.weavus.studyweb.entity.Post;
import com.weavus.studyweb.entity.StudyApplication;
import com.weavus.studyweb.entity.Studys;
import com.weavus.studyweb.service.StudyApplicationService;
import com.weavus.studyweb.service.StudysService;

@Component
public class CommonUtility {
    
    @Autowired
    private StudyApplicationService studyApplicationService;

    @Autowired
    private StudysService studysService;

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
            studysDetail.setStudys(StudysDTO.toStudysDTO(studys));
            studysDetail.setApplications(applications);
            studysDetail.setApplicantCount(applicantCount);
            studysDetailList.add(studysDetail);
        }

        return studysDetailList;
    }

    //Studys전체 리스트 취득
    public List<StudysDetail> getStudyListAll() {
        List<Studys> studysList = studysService.findAll();
        List<StudysDetail> studysDetails = toDetailList(studysList);
        return studysDetails;
    }

    //Studys 모집중 리스트 취득
    public List<StudysDetail> getStudyListRecruiting() {
        List<Studys> studysList = studysService.findRecruiting();
        List<StudysDetail> studysDetails = toDetailList(studysList);
        return studysDetails;
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
