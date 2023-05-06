package com.synergies.synergy.auth;

import com.synergies.synergy.domain.vo.LoginUserInfoVo;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class LoginAuth {

    public boolean isAuthorityCheck(HttpSession session) {
        int loginUserRole = ((LoginUserInfoVo) session.getAttribute("loginUserInfo")).getRole();
        return loginUserRole != 0;
    }

}
