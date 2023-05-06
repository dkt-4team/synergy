package com.synergies.synergy.service;

import com.synergies.synergy.auth.util.SaltUtil;
import com.synergies.synergy.auth.util.UUIDUtil;
import com.synergies.synergy.dao.UserDao;
import com.synergies.synergy.domain.dto.SignupDto;
import com.synergies.synergy.domain.vo.SignupVo;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupServiceImpl implements SignupService{

    @Autowired
    private UserDao userDao;

    @Override
    public int createUserSignupInfo(SignupDto signupDTO){
        String salt = SaltUtil.generateSalt();
        System.out.println(Arrays.toString(UUIDUtil.createUUID()));
        SignupVo signupVO = new SignupVo(
            UUIDUtil.createUUID(),
            signupDTO.getUserId(),
            SaltUtil.encodePassword(signupDTO.getPassword(), salt),
            salt,
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
