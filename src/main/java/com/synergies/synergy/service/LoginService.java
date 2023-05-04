package com.synergies.synergy.service;

import com.synergies.synergy.domain.vo.LoginUserInfoVo;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    public LoginUserInfoVo login(String userId, String password);

}
