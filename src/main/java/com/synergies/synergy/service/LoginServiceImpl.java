package com.synergies.synergy.service;

import com.synergies.synergy.auth.util.SaltUtil;
import com.synergies.synergy.dao.UserDao;
import com.synergies.synergy.domain.dto.LoginUserInfoDto;
import com.synergies.synergy.domain.vo.LoginUserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private UserDao userDao;

    @Override
    public LoginUserInfoDto login(String userId, String password) {
        Optional<LoginUserInfoVo> findUser = userDao.selectUserLoginInfo(userId);
        if (findUser.isEmpty()) {
            return new LoginUserInfoDto(false, false);
        }
        if (!(findUser.get().getPassword().equals(SaltUtil.encodePassword(password, findUser.get().getSalt())))) {
            return new LoginUserInfoDto(false, true);
        }
        return new LoginUserInfoDto(findUser.get());

    }
}
