package com.synergies.synergy.domain.dto;


import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TodoDto {
    private int id;
    private String refUserId;
    private String content;
    private Date regDate;
    private String endDate;

}
