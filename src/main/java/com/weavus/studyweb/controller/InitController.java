package com.weavus.studyweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.weavus.studyweb.dto.StudyDetail;
import com.weavus.studyweb.utility.CommonUtility;

@Controller
public class InitController {
    @Autowired
    private CommonUtility util;

    @GetMapping("/")
    private String init(Model model){
        // 모집중 스터디 리스트 취득
        List<StudyDetail> studyList = util.getStudyListRecruiting();

        model.addAttribute("studyList", studyList);
        return "index";
    }

    @GetMapping("/home")
    private String home(Model model){
        // 모집중 스터디 리스트 취득
        List<StudyDetail> studyList = util.getStudyListRecruiting();

        model.addAttribute("studyList", studyList);
        return "index";
    }

}
