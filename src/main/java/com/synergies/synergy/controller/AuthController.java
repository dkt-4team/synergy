package com.synergies.synergy.controller;

import com.synergies.synergy.domain.vo.LoginUserInfoVo;
import com.synergies.synergy.domain.dto.UserLoginRequestDto;
import com.synergies.synergy.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("loginUserInfo")
public class AuthController {

    @Autowired
    private LoginService loginService;

    @ModelAttribute("loginUserInfo")
    public LoginUserInfoVo createUserLoginInfoVO() {
        return new LoginUserInfoVo();
    }

    @GetMapping("/")
    public String loginPage(Model model) {
        model.addAttribute("userLoginRequest", new UserLoginRequestDto());
        model.addAttribute("test", 1234);
        return "loginPage";
    }

    @PostMapping("/login")
    public String userLogin(@ModelAttribute("userLoginRequest") UserLoginRequestDto userLoginRequest, Model model) {
        System.out.println(userLoginRequest.getUserId());
        LoginUserInfoVo userInfo = loginService.login(userLoginRequest.getUserId(), userLoginRequest.getPassword());
        if (userInfo != null) {
            model.addAttribute("loginUserInfo", userInfo);
            return "redirect:/home";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String userLogout(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
