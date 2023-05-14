package com.synergies.synergy.dao;

import com.synergies.synergy.domain.vo.LoginUserInfoVo;
import com.synergies.synergy.domain.vo.SignupVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserDao {

    int insertSignupUserInfo(SignupVo signupVO);

    Optional<LoginUserInfoVo> selectUserLoginInfo(String userId);

    int selectDuplicationUser(String userId);

}
