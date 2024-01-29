package com.weavus.studyweb.controller;

import com.weavus.studyweb.dto.UserJoinDTO;
import com.weavus.studyweb.entity.User;
import com.weavus.studyweb.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @GetMapping("/loginForm")
    private String login() {

        return "user/login";
    }

    @GetMapping("/login/fail")
    private String loginFail(Model model) {

        model.addAttribute("msg", "로그인에 실패했습니다.\r\n 아이디와 패스워드를 확인해주세요.");

        return "user/login";
    }

    // 회원가입
    @GetMapping("/join")
    private String join() {

        return "user/join";
    }

    @PostMapping("/join/reg")
    private String reg(@Valid UserJoinDTO userJoinDto, BindingResult bindingResult, RedirectAttributes redirectAttrs) {

        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                String field = error.getField();
                String message = error.getDefaultMessage();
                
                if (field.equals("userid")) {
                    redirectAttrs.addFlashAttribute("useridError", message);
                } else if (field.equals("username")) {
                    redirectAttrs.addFlashAttribute("usernameError", message);
                } else if (field.equals("password")) {
                    if (message.equals("비밀번호를 입력해주세요.")) {
                        redirectAttrs.addFlashAttribute("passwordEmptyError", message);
                    } else if (message.equals("비밀번호는 영문, 숫자를 포함한 8~20자리로 입력해주세요.")) {
                        redirectAttrs.addFlashAttribute("passwordPatternError", message);
                    }
                }
            }
            return "redirect:/join";
        }

        User user = new User();
        user.setUserid(userJoinDto.getUserid());
        user.setUsername(userJoinDto.getUsername());
        user.setPassword(userJoinDto.getPassword());

        user.setRole("USER");

        String encPw = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encPw);

        User result = userRepository.save(user);

        System.out.println(result);
        if (result != null) {

            return "redirect:/loginForm";
        } else {
            return "index";
        }
    }
}
