package com.synergies.synergy.auth;

import com.synergies.synergy.domain.vo.LoginUserInfoVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class LoginAuth {

    public boolean isAuthorityCheck(HttpSession session) {
        int loginUserRole = ((LoginUserInfoVo) session.getAttribute("loginUserInfo")).getRole();
        return loginUserRole != 0;
    }
}
