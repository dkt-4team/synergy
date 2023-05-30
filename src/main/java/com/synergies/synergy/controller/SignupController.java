package com.synergies.synergy.controller;

import com.synergies.synergy.domain.dto.SignupDto;
import com.synergies.synergy.service.SignupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class SignupController {

    @Autowired
    private SignupService signupService;

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("signupInfoDto", new SignupDto());
        return "signupPage";
    }

    @PostMapping("/signup")
    public String userSignup(@ModelAttribute("signupInfoDto") SignupDto signupDTO) {
        int result = signupService.createUserSignupInfo(signupDTO);
        if (result == 1) {
            log.info("회원가입 성공 + 회원가입 데이터 : " + signupDTO);
            return "redirect:/";
        }
        return "signupPage";
    }

    //유저 아이디 중복확인 ajax요청
    @PostMapping("/check/userId")
    @ResponseBody
    public boolean checkUserId(@RequestBody String userId) {
        int checkResult = signupService.readDuplicationUser(userId);
        if (checkResult == 0) {
            return true;
        }
        return false;
    }
}