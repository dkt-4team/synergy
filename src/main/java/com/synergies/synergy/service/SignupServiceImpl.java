package com.synergies.synergy.service;

import com.synergies.synergy.dao.UserDao;
import com.synergies.synergy.domain.dto.SignupDto;
import com.synergies.synergy.domain.vo.SignupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupServiceImpl implements SignupService{

    @Autowired
    private UserDao userDao;

    @Override
    public int createUserSignupInfo(SignupDto signupDTO){
        SignupVo signupVO = new SignupVo(
            signupDTO.getId(),
            signupDTO.getUserId(),
            signupDTO.getPassword(),
            signupDTO.getName(),
            signupDTO.getEmail(),
            signupDTO.getPhoneNumber(),
            signupDTO.getRole()
        );
        return userDao.insertSignupUserInfo(signupVO);
    }

    @Override
    public int readDuplicationUser(String userId){
        return userDao.selectDuplicationUser(userId);
    }
}
