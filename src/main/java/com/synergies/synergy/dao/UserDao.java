package com.synergies.synergy.dao;

import com.synergies.synergy.domain.dto.SignupDTO;
import com.synergies.synergy.domain.vo.LoginUserInfoVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    int insertSignupUserInfo(SignupDTO signupDTO);

    LoginUserInfoVO selectUserLoginInfo(String userId);

    int selectDuplicationUser(String userId);

}
