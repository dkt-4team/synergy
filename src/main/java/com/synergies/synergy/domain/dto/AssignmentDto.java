package com.synergies.synergy.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@ToString
@Getter
@Setter
public class AssignmentDto {
    private int id;
    private String title;
    private String content;
    private MultipartFile file;
    private int assignmentNumber;

    public AssignmentDto(int assignmentNumber) {
        this.assignmentNumber = assignmentNumber;
    }
}
