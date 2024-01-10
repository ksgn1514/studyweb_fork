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
import com.weavus.studyweb.dto.StudysDTO;
import com.weavus.studyweb.dto.StudysDetail;
import com.weavus.studyweb.entity.StudyApplication;
import com.weavus.studyweb.entity.Studys;
import com.weavus.studyweb.entity.User;
import com.weavus.studyweb.service.StudyApplicationService;
import com.weavus.studyweb.service.StudysService;
import com.weavus.studyweb.utility.CommonUtility;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/studys")
public class StudysController {
    
    @Autowired
    private StudysService studysService;
    @Autowired
    private StudyApplicationService studyApplicationService;
    @Autowired
    private CommonUtility util;

    @GetMapping("")
    private String studyAll(Model model){
        // Studys 전체 리스트 취득
        List<StudysDetail> studysDetails = util.getStudyListAll();

        model.addAttribute("studysList", studysDetails);
        
        return "study/studys";
    }
    
    //continue파라미터가 있을 경우 스터디 계속하기 페이지로 이동
    @GetMapping(params = "continue")
    private String studysContinue(Model model){
        // Studys 전체 리스트 취득
        List<StudysDetail> studysDetails = util.getStudyListAll();

        model.addAttribute("studysList", studysDetails);
        return "study/studys";
    }

    //스터디 필터링
    @GetMapping("filter")
    public String filterStudys(@RequestParam String status, Model model) {
        List<StudysDetail> studysDetails = new ArrayList<>();

        // "모집중" 스터디 목록 취득
        if ("recruiting".equals(status)){
            studysDetails = util.getStudyListRecruiting();
        } else {
            // "전체" 또는 유효하지 않은 status 값에 대해서는 전체 목록 반환
            studysDetails = util.getStudyListAll();
        }

        model.addAttribute("studysList", studysDetails);
        return "study/studys :: studys-list";
    }
    

    //스터디 등록 페이지 이동
    @GetMapping("enroll")
    private String studyenroll() {

        return "study/studyenroll";
    }
    //스터디 등록
    @PostMapping("create")
    public String createStudy(@Validated @ModelAttribute StudysDTO studysDto, MultipartFile file) throws IllegalStateException, IOException {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User loginUser = principalDetails.getUser();

        Studys studys = new Studys();
        
        studys.setStudyName(studysDto.getStudyName());
        studys.setCategory(studysDto.getCategory());
        studys.setStudyDetail(studysDto.getStudyDetail());
        studys.setStartDate(studysDto.getTsStartDate());
        studys.setEndDate(studysDto.getTsEndDate());
        studys.setWriterUserid(loginUser.getUserid());

        System.out.println(file.getOriginalFilename());
        if (file.getOriginalFilename() != "") {
            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\assets\\img\\study";

            UUID uuid = UUID.randomUUID();
            String temp = file.getOriginalFilename();
            String fileName =  "study_" + uuid + temp.substring(file.getOriginalFilename().lastIndexOf("."));

            File saveFile = new File(projectPath, fileName);

            file.transferTo(saveFile);

            studys.setFilepath("/assets/img/study/" + fileName);
        }

        //@TODO 실패시 처리 성공시 입력값반환. 
        Studys result = studysService.createStudy(studys);
        System.out.println(result);
        
        return "redirect:/study/studys";
    }
    

    @GetMapping("detail")// studys/detail?id=
    private String studydetail(Model model, @RequestParam("id") Long id) {

        StudysDTO study = StudysDTO.toStudysDTO(studysService.findById(id));
        study.setStudyDetail(util.nl2br(study.getStudyDetail()));

        System.out.println(study);

        model.addAttribute("study", study);

        List<StudyApplication> applications = studyApplicationService.findByStudy(studysService.findById(id));
        int applicantCount = applications.size();
        
        model.addAttribute("applications", applications);
        model.addAttribute("applicantCount", applicantCount);

        return "study/studydetail";
    }

    @PostMapping("apply")// studys/apply
    public String applyStudy(@RequestParam("id") Long studyId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User loginUser = principalDetails.getUser();

        Studys study = new Studys();
        study = studysService.findById(studyId);

        StudyApplication studyApplication = new StudyApplication();
        studyApplication.setStudy(study);
        studyApplication.setUser(loginUser);

        studyApplicationService.addStudyApplication(studyApplication);
        
        return "redirect:/study/studys";
    }
    

}
