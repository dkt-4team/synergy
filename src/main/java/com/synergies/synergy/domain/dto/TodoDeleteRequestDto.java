package com.synergies.synergy.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TodoDeleteRequestDto {
    private int id;
    private byte[] refUserId;

}
