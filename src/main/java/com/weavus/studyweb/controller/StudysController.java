package com.weavus.studyweb.controller;

import java.io.File;
import java.io.IOException;
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
import com.weavus.studyweb.entity.Studys;
import com.weavus.studyweb.entity.User;
import com.weavus.studyweb.service.StudysService;
import com.weavus.studyweb.utility.CommonUtility;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/studys")
public class StudysController {
    
    @Autowired
    private StudysService studysService;
    @Autowired
    private CommonUtility util;

    @GetMapping("")
    private String studys(Model model){
        // Studysのリストの取得
        List<Studys> studysList = studysService.findAll();
        studysList.forEach(studys -> studys.setStudyDetail(util.nl2br(studys.getStudyDetail())));
        
        model.addAttribute("studysList", studysList);
        
        return "studys";
    }
    
    //continueクエリパラメータに対応
    @GetMapping(params = "continue")
    private String studysContinue(Model model){
        // Studysのリストの取得
        List<Studys> studysList = studysService.findAll();
        studysList.forEach(studys -> studys.setStudyDetail(util.nl2br(studys.getStudyDetail())));
        
        
        model.addAttribute("studysList", studysList);
        return "studys";
    }

    //스터디 등록 페이지 이동
    @GetMapping("enroll")
    private String studyenroll() {

        return "studyenroll";
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
        studys.setStartDate(studysDto.getStartDate());
        studys.setEndDate(studysDto.getEndDate());
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

        
        studysService.createStudy(studys);
        
        return "redirect:/studys";
    }
    

    @GetMapping("detail")// studys/detail?id=
    private String studydetail(Model model, Long id) {

        StudysDTO study = StudysDTO.toStudysDTO(studysService.findById(id));
        study.setStudyDetail(util.nl2br(study.getStudyDetail()));
        
        model.addAttribute("study", study);

        return "studydetail";
    }

}
