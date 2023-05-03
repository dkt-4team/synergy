package com.synergies.synergy.controller;

import com.synergies.synergy.domain.vo.LoginUserInfoVO;
import com.synergies.synergy.domain.dto.UserLoginRequestDTO;
import com.synergies.synergy.service.AuthService;
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
    private AuthService authService;

    @ModelAttribute("loginUserInfo")
    public LoginUserInfoVO createUserLoginInfoVO() {
        return new LoginUserInfoVO();
    }

    @GetMapping("/")
    public String loginPage(Model model) {
        model.addAttribute("userLoginRequest", new UserLoginRequestDTO());
        model.addAttribute("test", 1234);
        return "loginPage";
    }

    @PostMapping("/login")
    public String userLogin(@ModelAttribute UserLoginRequestDTO userLoginRequest, Model model) {
        LoginUserInfoVO userInfo = authService.readUserLoginInfo(userLoginRequest.getUserId());
        if (userInfo != null && userInfo.getPassword().equals(userLoginRequest.getPassword())) {
            LoginUserInfoVO loginUserInfoVO = new LoginUserInfoVO(userInfo.getUserId(),
                userInfo.getPassword(), userInfo.getName(),
                userInfo.getEmail(), userInfo.getPhoneNumber());
            model.addAttribute("loginUserInfo", loginUserInfoVO);
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
