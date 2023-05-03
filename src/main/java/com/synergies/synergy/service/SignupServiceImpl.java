package com.synergies.synergy.service;

import com.synergies.synergy.dao.UserDao;
import com.synergies.synergy.domain.dto.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupServiceImpl implements SignupService{

    @Autowired
    private UserDao userDao;

    @Override
    public int createUserSignupInfo(SignupDTO signupDTO){
        return userDao.insertSignupUserInfo(signupDTO);
    }

    @Override
    public int readDuplicationUser(String userId){
        return userDao.selectDuplicationUser(userId);
    }
}
