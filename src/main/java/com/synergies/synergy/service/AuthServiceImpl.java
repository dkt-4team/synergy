package com.synergies.synergy.service;

import com.synergies.synergy.dao.UserDao;
import com.synergies.synergy.domain.vo.LoginUserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserDao userDao;

    @Override
    public LoginUserInfoVO readUserLoginInfo(String userId){
        return userDao.selectUserLoginInfo(userId);
    }
}
