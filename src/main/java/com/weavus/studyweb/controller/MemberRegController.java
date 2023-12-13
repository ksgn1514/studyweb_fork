package com.weavus.studyweb.controller;

import com.weavus.studyweb.entity.User;
import com.weavus.studyweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberRegController {


    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @PostMapping("/reg")
    private String reg(User user) {

        user.setRole("USER");

        String encPw = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encPw);

        userRepository.save(user);
        return "redirect:/loginForm";

    }
}
