package com.synergies.synergy.domain.vo;

import com.synergies.synergy.domain.dto.LoginUserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginUserInfoVo {

    private byte[] id;

    private String userId;

    private String password;

    private String salt;

    private String name;

    private String email;

    private String phoneNumber;

    private int role;

    public LoginUserInfoVo(LoginUserInfoDto loginUserInfoDto) {
        this.id = loginUserInfoDto.getId();
        this.userId = loginUserInfoDto.getUserId();
        this.password = loginUserInfoDto.getPassword();
        this.salt = loginUserInfoDto.getSalt();
        this.name = loginUserInfoDto.getName();
        this.email = loginUserInfoDto.getEmail();
        this.phoneNumber = loginUserInfoDto.getPhoneNumber();
        this.role = loginUserInfoDto.getRole();
    }
}
