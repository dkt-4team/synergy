package com.synergies.synergy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupVo {
    private byte[] id;
    private String userId;
    private String password;
    private String salt;
    private String name;
    private String email;
    private String phoneNumber;
    private int role;
}
