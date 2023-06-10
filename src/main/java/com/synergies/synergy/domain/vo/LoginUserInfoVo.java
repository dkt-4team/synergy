package com.synergies.synergy.domain.vo;

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

}
