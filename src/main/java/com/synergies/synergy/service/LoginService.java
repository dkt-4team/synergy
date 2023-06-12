package com.synergies.synergy.service;

import com.synergies.synergy.domain.dto.LoginUserInfoDto;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    LoginUserInfoDto login(String userId, String password);

}
