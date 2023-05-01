package com.synergies.synergy.domain.dto;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class NotificationDto {
    private int id;
    private String ref_user_id;
    private String title;
    private String content;
    private String labelOption;

    public String getLabelOption() {
        return labelOption;
    }

    public void setLabelOption(String labelOption) {
        this.labelOption = labelOption;
    }

    private String reg_date;

    public String getRef_user_id() {
        return ref_user_id;
    }

    public void setRef_user_id(String ref_user_id) {
        this.ref_user_id = ref_user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public NotificationDto() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public NotificationDto(int id) {
        this.id = id;
    }

    public NotificationDto(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public String formattedRegDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateTime = LocalDateTime.parse(this.reg_date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return dateTime.format(formatter);
    }
}
