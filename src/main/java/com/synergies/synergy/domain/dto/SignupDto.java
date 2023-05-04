package com.synergies.synergy.domain.dto;

import com.synergies.synergy.util.UUIDConvertor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDto {
    private byte[] id;
    private String userId;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private int role;

    public SignupDto(){
        this.id = UUIDConvertor.createUUID();
    }

}
