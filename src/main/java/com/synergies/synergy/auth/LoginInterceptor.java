package com.synergies.synergy.auth;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

    public List loginEssential = Arrays.asList("/home/**", "/todo/**", "/adminMain");
    public List loginInessential = Arrays.asList("/login/**", "/");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj)
        throws Exception {
        boolean sessionCheck = Optional.ofNullable(
            request.getSession().getAttribute("loginUserInfo")).isEmpty();
        if (sessionCheck) {
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
}
