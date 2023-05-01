package com.synergies.synergy.domain.dto;


import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    private int id;
    private String refUserId;
    private String content;
    private Date regDate;
    private String endDate;
}
