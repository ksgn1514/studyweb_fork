package com.weavus.studyweb.controller;

import com.weavus.studyweb.entity.User;
import com.weavus.studyweb.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    

    @GetMapping("/join")
    private String join() {

        return "user/join";
    }
    
    
    @GetMapping("/loginForm")
    private String login() {

        return "user/login";
    }

    @GetMapping("/login/fail")
    private String loginFail(Model model){

        model.addAttribute("msg", "로그인에 실패했습니다.\r\n 아이디와 패스워드를 확인해주세요.");

        return "user/login";
    }

    //회원가입
    @PostMapping("/join/reg")
    private String reg(User user) {

        user.setRole("USER");

        String encPw = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encPw);

        User result = userRepository.save(user);

        System.out.println(result);
        if(result != null){
            
        return "redirect:/loginForm";
        } else {
            return "index";
        }
    }
}
