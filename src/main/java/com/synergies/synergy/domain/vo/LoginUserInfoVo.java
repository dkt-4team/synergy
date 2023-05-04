package com.synergies.synergy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserInfoVO {

    private String userId;
    private String password;

    private String name;

    private String email;

    private String phoneNumber;

}
