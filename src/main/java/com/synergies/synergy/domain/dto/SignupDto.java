package com.synergies.synergy.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignupDto {
    private byte[] id;
    private String userId;
    private String password;
    private String salt;
    private String name;
    private String email;
    private String phoneNumber;
    private int role;

}
