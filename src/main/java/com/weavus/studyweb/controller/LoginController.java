package com.weavus.studyweb.controller;

import com.weavus.studyweb.dto.MemberDto;
import com.weavus.studyweb.entity.Member;
import com.weavus.studyweb.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private MemberRepo memberRepo;

//    @PostMapping("/login")
//    private String login(MemberDto dto, Model model) {
//
//        Member member = memberRepo.findByIdAndPassword(dto.getId(), dto.getPassword());
//
//
//        if(member != null) {
//            return "redirect:/" + "home";
//        } else {
//            model.addAttribute("msg", "아이디 또는 패스워드를 확인해주세요");
//            return "login";
//        }
//    }
}
