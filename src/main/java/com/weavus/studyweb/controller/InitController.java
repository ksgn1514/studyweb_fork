package com.weavus.studyweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.weavus.studyweb.dto.StudysDetail;
import com.weavus.studyweb.utility.CommonUtility;

@Controller
public class InitController {
    @Autowired
    private CommonUtility util;

    @GetMapping("/")
    private String init(Model model){
        // 모집중 스터디 리스트 취득
        List<StudysDetail> studysList = util.getStudyListRecruiting();

        model.addAttribute("studysList", studysList);
        return "index";
    }

    @GetMapping("/home")
    private String home(Model model){
        // 모집중 스터디 리스트 취득
        List<StudysDetail> studysList = util.getStudyListRecruiting();

        model.addAttribute("studysList", studysList);
        return "index";
    }

    
    @GetMapping("/loginForm")
    private String login() {

        return "user/login";
    }

//Community 커뮤니티 작성시 파일 분할.
    @GetMapping("/community")
    private String community(){
        
        return "community/community";
    }
    //continueクエリパラメータに対応
    @GetMapping("/community?continue")
    private String communityContinue(){

        return "community/community";
    }

}
