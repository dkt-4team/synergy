package com.synergies.synergy.service;

import com.synergies.synergy.domain.dto.SignupDTO;
import org.springframework.stereotype.Service;

@Service
public interface SignupService {

    int createUserSignupInfo(SignupDTO signupDTO);

    int readDuplicationUser(String userId);

}
