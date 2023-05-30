package com.synergies.synergy.service;

import com.synergies.synergy.auth.util.SaltUtil;
import com.synergies.synergy.dao.UserDao;
import com.synergies.synergy.domain.vo.LoginUserInfoVo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private UserDao userDao;

    @Override
    public LoginUserInfoVo login(String userId, String password){
        Optional<LoginUserInfoVo> findUser = userDao.selectUserLoginInfo(userId);
        return findUser.filter(i->i.getPassword().equals(SaltUtil.encodePassword(password, i.getSalt()))).orElse(null);

    }
}
