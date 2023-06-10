package com.synergies.synergy.auth;

import com.synergies.synergy.domain.vo.LoginUserInfoVo;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class LoginInterceptor implements HandlerInterceptor {

    public List<String> loginEssential = Arrays.asList("/admin/**", "/student/**");

    public List<String> loginInessential = Arrays.asList("/login/**", "/");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj)
            throws Exception {
        boolean sessionCheck = Optional.ofNullable(
                request.getSession().getAttribute("loginUserInfo")).isEmpty();
        if (sessionCheck) {
            response.sendRedirect("/");
            return false;
        }
        Optional<String> requestUri = Optional.ofNullable(request.getRequestURI().split("/")[1]);
        int userRole = ((LoginUserInfoVo) request.getSession().getAttribute("loginUserInfo")).getRole();
        if (requestUri.get().equals("admin") && userRole != 1) {
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
}
