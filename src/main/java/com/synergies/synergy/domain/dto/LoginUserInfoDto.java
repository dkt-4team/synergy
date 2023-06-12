package com.synergies.synergy.domain.dto;

import com.synergies.synergy.domain.vo.LoginUserInfoVo;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginUserInfoDto {

    private byte[] id;

    private String userId;

    private String password;

    private String salt;

    private String name;

    private String email;

    private String phoneNumber;

    private int role;

    private boolean passwordCheck;

    private boolean idCheck;

    public LoginUserInfoDto(boolean passwordCheck, boolean idCheck) {
        this.passwordCheck = passwordCheck;
        this.idCheck = idCheck;
    }

    public LoginUserInfoDto(LoginUserInfoVo loginUserInfoVo) {
        this.id = loginUserInfoVo.getId();
        this.userId = loginUserInfoVo.getUserId();
        this.password = loginUserInfoVo.getPassword();
        this.salt = loginUserInfoVo.getSalt();
        this.name = loginUserInfoVo.getName();
        this.email = loginUserInfoVo.getEmail();
        this.phoneNumber = loginUserInfoVo.getPhoneNumber();
        this.role = loginUserInfoVo.getRole();
        this.passwordCheck = true;
        this.idCheck = true;
    }

    public boolean isId() {
        return idCheck;
    }

    public boolean isPassword() {
        return passwordCheck;
    }
}
