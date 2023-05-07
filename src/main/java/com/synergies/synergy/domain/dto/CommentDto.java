package com.synergies.synergy.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CommentDto {
    private int submitId;
    private String content;

    public CommentDto(int submitId) {
        this.submitId = submitId;
    }
}
