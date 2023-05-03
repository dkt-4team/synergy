package com.synergies.synergy.service;

import com.synergies.synergy.domain.vo.LoginUserInfoVO;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    LoginUserInfoVO readUserLoginInfo(String userId);

}
