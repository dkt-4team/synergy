package com.synergies.synergy.domain.dto;


import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {

    private int id;
    private byte[] refUserId;
    private String title;
    private String content;
    private String regDate;
    private String labelOption;

}
