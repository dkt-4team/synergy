package com.synergies.synergy.dao;

import com.synergies.synergy.domain.vo.LoginUserInfoVo;
import com.synergies.synergy.domain.vo.SignupVo;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    int insertSignupUserInfo(SignupVo signupVO);

    Optional<LoginUserInfoVo> selectUserLoginInfo(String userId);

    int selectDuplicationUser(String userId);

}
