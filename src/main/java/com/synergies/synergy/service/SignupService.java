package com.synergies.synergy.service;

import com.synergies.synergy.domain.dto.SignupDto;
import org.springframework.stereotype.Service;

@Service
public interface SignupService {

    int createUserSignupInfo(SignupDto signupDTO);

    int readDuplicationUser(String userId);
}
