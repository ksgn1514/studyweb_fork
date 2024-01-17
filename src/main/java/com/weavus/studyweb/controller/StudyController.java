package com.weavus.studyweb.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.weavus.studyweb.auth.PrincipalDetails;
import com.weavus.studyweb.dto.StudyDTO;
import com.weavus.studyweb.dto.StudyDetail;
import com.weavus.studyweb.entity.StudyApplication;
import com.weavus.studyweb.entity.Study;
import com.weavus.studyweb.entity.User;
import com.weavus.studyweb.service.StudyApplicationService;
import com.weavus.studyweb.service.StudyService;
import com.weavus.studyweb.utility.CommonUtility;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/study")
public class StudyController {
    
    @Autowired
    private StudyService studyService;
    @Autowired
    private StudyApplicationService studyApplicationService;
    @Autowired
    private CommonUtility util;

    @GetMapping("") //continue파라미터 포함
    private String studyAll(@RequestParam(required = false) String conti, Model model){
        // Study 전체 리스트 취득
        List<StudyDetail> studyDetails = util.getStudyListAll();

        model.addAttribute("studyList", studyDetails);
        
        return "study/study";
    }
    
    //스터디 필터링
    @GetMapping("filter")
    public String filterStudy(@RequestParam String status, Model model) {
        List<StudyDetail> studyDetails = new ArrayList<>();

        // "모집중" 스터디 목록 취득
        if ("recruiting".equals(status)){
            studyDetails = util.getStudyListRecruiting();
        } else {
            // "전체" 또는 유효하지 않은 status 값에 대해서는 전체 목록 반환
            studyDetails = util.getStudyListAll();
        }

        model.addAttribute("studyList", studyDetails);
        return "study/study :: study-list";
    }
    

    //스터디 등록 페이지 이동
    @GetMapping("enroll")
    private String studyenroll() {

        return "study/studyenroll";
    }
    //스터디 등록
    @PostMapping("create")
    public String createStudy(@Validated @ModelAttribute StudyDTO studyDto, MultipartFile file) throws IllegalStateException, IOException {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User loginUser = principalDetails.getUser();

        Study study = new Study();
        
        study.setStudyName(studyDto.getStudyName());
        study.setCategory(studyDto.getCategory());
        study.setStudyDetail(studyDto.getStudyDetail());
        study.setStartDate(studyDto.getTsStartDate());
        study.setEndDate(studyDto.getTsEndDate());
        study.setWriterUserid(loginUser.getUserid());

        System.out.println(file.getOriginalFilename());
        if (file.getOriginalFilename() != "") {
            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\assets\\img\\study";

            UUID uuid = UUID.randomUUID();
            String temp = file.getOriginalFilename();
            String fileName =  "study_" + uuid + temp.substring(file.getOriginalFilename().lastIndexOf("."));

            File saveFile = new File(projectPath, fileName);

            file.transferTo(saveFile);

            study.setFilepath("/assets/img/study/" + fileName);
        }

        //@TODO 실패시 처리 성공시 입력값반환. 
        Study result = studyService.createStudy(study);
        System.out.println(result);
        
        return "redirect:/study";
    }
    

    @GetMapping("detail")// study/detail?id=
    private String studydetail(Model model, @RequestParam("id") Long id) {

        StudyDTO study = StudyDTO.toStudyDTO(studyService.findById(id));
        study.setStudyDetail(util.nl2br(study.getStudyDetail()));

        System.out.println(study);

        model.addAttribute("study", study);

        List<StudyApplication> applications = studyApplicationService.findByStudy(studyService.findById(id));
        int applicantCount = applications.size();
        
        model.addAttribute("applications", applications);
        model.addAttribute("applicantCount", applicantCount);

        return "study/studydetail";
    }

    @PostMapping("apply")// study/apply
    public String applyStudy(@RequestParam("id") Long studyId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User loginUser = principalDetails.getUser();

        Study study = new Study();
        study = studyService.findById(studyId);

        StudyApplication studyApplication = new StudyApplication();
        studyApplication.setStudy(study);
        studyApplication.setUser(loginUser);

        studyApplicationService.addStudyApplication(studyApplication);
        
        return "redirect:/study";
    }
    

}
