package com.synergies.synergy.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class NotificationDto {
    private int id;
    private String ref_user_id;
    private String title;
    private String content;
    private String labelOption;
}
