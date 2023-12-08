package com.weavus.studyweb.controller;

import com.weavus.studyweb.dto.MemberDto;
import com.weavus.studyweb.entity.Member;
import com.weavus.studyweb.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reg")
public class MemberRegController {

    @Autowired
    private MemberRepo memberRepo;

    @PostMapping("/reg")
    private String reg(MemberDto dto, Model model) {

        Member member = Member.builder().id(dto.getId()).name(dto.getName()).password(dto.getPassword()).build();
        model.addAttribute("msg", "회원가입이 완료 되었습니다");
        memberRepo.save(member);

        return "redirect:/login";
    }
}
