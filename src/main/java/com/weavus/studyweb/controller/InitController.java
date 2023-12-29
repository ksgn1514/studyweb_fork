package com.weavus.studyweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.weavus.studyweb.entity.Studys;
import com.weavus.studyweb.service.StudysService;
import com.weavus.studyweb.utility.CommonUtility;
import com.weavus.studyweb.utility.StudysDetail;

@Controller
public class InitController {
    @Autowired
    private StudysService studysService;
    @Autowired
    private CommonUtility util;

    @GetMapping("/")
    private String init(Model model){
        // Studysのリストの取得
        List<Studys> studysList = studysService.findRecruiting();
        List<StudysDetail> studysDetails = util.toDetailList(studysList);

        model.addAttribute("studysList", studysDetails);
        return "index";
    }

    @GetMapping("/home")
    private String home(Model model){
        // Studysのリストの取得
        List<Studys> studysList = studysService.findRecruiting();
        List<StudysDetail> studysDetails = util.toDetailList(studysList);

        model.addAttribute("studysList", studysDetails);
        return "index";
    }

    
    @GetMapping("/loginForm")
    private String login() {

        return "login";
    }

//Community 今後別途ファイルに分割
    @GetMapping("/community")
    private String community(){
        
        return "community";
    }
    //continueクエリパラメータに対応
    @GetMapping("/community?continue")
    private String communityContinue(){

        return "community";
    }

}
