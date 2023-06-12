package com.synergies.synergy.controller;

import com.synergies.synergy.auth.LoginAuth;
import com.synergies.synergy.domain.dto.LoginUserInfoDto;
import com.synergies.synergy.domain.dto.UserLoginRequestDto;
import com.synergies.synergy.domain.vo.LoginUserInfoVo;
import com.synergies.synergy.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@Controller
public class AuthController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private LoginAuth loginAuth;

    @GetMapping("/")
    public String loginPage(Model model, HttpSession session) {
        Optional<Object> userCheck = Optional.ofNullable(session.getAttribute("loginUserInfo"));
        log.info("서비스 접속 시 로그인 여부 확인 : " + !userCheck.isEmpty());
        if (!userCheck.isEmpty()) {
            log.info("접속한 유저의 권한 확인(관리자) : " + loginAuth.isAuthorityCheck(session));
            if (!loginAuth.isAuthorityCheck(session)) {
                return "redirect:/student/home";
            }
            return "redirect:/admin/home";
        }
        model.addAttribute("userLoginRequest", new UserLoginRequestDto());
        return "loginPage";
    }

    @PostMapping("/login")
    public String userLogin(
            @ModelAttribute("userLoginRequest") UserLoginRequestDto userLoginRequest,
            HttpSession session) {
        LoginUserInfoDto userInfo = loginService.login(userLoginRequest.getUserId(),
                userLoginRequest.getPassword());
        if (userInfo.isId() && userInfo.isPassword()) {
            LoginUserInfoVo loginUserInfo = new LoginUserInfoVo(userInfo);
            log.info("유저 정보 확인 : " + userInfo);
            session.setAttribute("loginUserInfo", loginUserInfo);
            if (!loginAuth.isAuthorityCheck(session)) {
                return "redirect:/student/home";
            }
            return "redirect:/admin/home";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String userLogout(HttpSession session) {
        session.removeAttribute("loginUserInfo");
        log.info("세션 종료 확인(로그아웃) : " + (session.getAttribute("loginUserInfo") == null));
        return "redirect:/";
    }
}
