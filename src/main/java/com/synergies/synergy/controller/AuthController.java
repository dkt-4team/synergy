package com.synergies.synergy.controller;

import com.synergies.synergy.auth.LoginAuth;
import com.synergies.synergy.domain.dto.UserLoginRequestDto;
import com.synergies.synergy.domain.vo.LoginUserInfoVo;
import com.synergies.synergy.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private LoginAuth loginAuth;

    @GetMapping("/")
    public String loginPage(Model model, HttpSession session) {
        Optional<Object> userCheck = Optional.ofNullable(session.getAttribute("loginUserInfo"));
        if (!userCheck.isEmpty()) {
            if (!loginAuth.isAuthorityCheck(session)) {
                return "redirect:/home";
            }
            return "redirect:/adminMain";
        }
        model.addAttribute("userLoginRequest", new UserLoginRequestDto());
        return "loginPage";
    }

    @PostMapping("/login")
    public String userLogin(
        @ModelAttribute("userLoginRequest") UserLoginRequestDto userLoginRequest,
        HttpSession session) {
        LoginUserInfoVo userInfo = loginService.login(userLoginRequest.getUserId(),
            userLoginRequest.getPassword());
        if (userInfo != null) {
            session.setAttribute("loginUserInfo", userInfo);
            if (!loginAuth.isAuthorityCheck(session)) {
                return "redirect:/home";
            }
            return "redirect:/adminMain";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String userLogout(HttpSession session) {
        session.removeAttribute("loginUserInfo");
        return "redirect:/";
    }
}
