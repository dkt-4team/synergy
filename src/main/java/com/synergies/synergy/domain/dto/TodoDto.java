package com.synergies.synergy.domain.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TodoDto {
    private int id;
    private byte[] refUserId;
    private String content;
    private Date regDate;
    private String endDate;

}
