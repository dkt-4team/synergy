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
    private Date regDate = new Date();
    private String endDate;

    public TodoDto(int id) {
        this.id = id;
    }
    public TodoDto(String refUserId, String content, Date regDate, String endDate){
        this.refUserId = refUserId;
        this.content = content;
        this.regDate = regDate;
        this.endDate = endDate;
    }
}
