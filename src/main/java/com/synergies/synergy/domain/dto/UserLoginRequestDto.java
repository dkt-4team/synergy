package com.synergies.synergy.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequestDto {
    private String userId;
    private String password;

}
