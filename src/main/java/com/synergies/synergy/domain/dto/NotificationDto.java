package com.synergies.synergy.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class NotificationDto {
    private int id;
    private String refUserId;
    private String title;
    private String content;
    private String regDate;
    private String labelOption;
}
