package com.synergies.synergy.controller;

import com.synergies.synergy.domain.dto.SignupDTO;
import com.synergies.synergy.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SignupController {

    @Autowired
    private SignupService signupService;
    @GetMapping("/signup")
    public String signupPage(Model model){
        model.addAttribute("signupInfoDto", new SignupDTO());
        return "signupPage";
    }

    @PostMapping("/signup")
    public String userSignup(@ModelAttribute("signupInfoDto") SignupDTO signupDTO){
        int result = signupService.createUserSignupInfo(signupDTO);
        if(result == 1){
            return "redirect:/";
        }
        return "signupPage";
    }

    //유저 아이디 중복확인 ajax요청
    @PostMapping("/check/userId")
    @ResponseBody
    public boolean checkUserId(@RequestBody String userId){
        int checkResult = signupService.readDuplicationUser(userId);
        if(checkResult == 0){
            return true;
        }
        return false;
    }
}
