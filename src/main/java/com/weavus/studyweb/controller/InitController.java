package com.weavus.studyweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InitController {

    @GetMapping("/")
    private String init(){

        return "index";
    }

    @GetMapping("/home")
    private String home(){
        
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
