package com.weavus.studyweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.weavus.studyweb.entity.Studys;
import com.weavus.studyweb.service.StudysService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/studys")
public class StudysController {
    
    @Autowired
    private StudysService studysService;

    @GetMapping("")
    private String studys() {

        return "studys";
    }

    //스터디 등록 페이지 이동
    @GetMapping("enroll")
    private String studyenroll() {

        return "studyenroll";
    }
    //스터디 등록
    @PostMapping("create")
    public String createStudy(Studys study) {
        
        System.out.println(study);
        if(study == null){
            return "redirect:/";
        }
        studysService.createStudy(study);
        
        return "redirect:/studys";
    }
    

    @GetMapping("detail")// studys/detail?id=
    private String studydetail(Long id) {

        return "studydetail";
    }

}
