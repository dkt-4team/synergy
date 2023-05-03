package com.synergies.synergy.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Getter
@Setter
public class AssignmentDto {
    private int id;
    private String title;
    private String content;
    private MultipartFile file;

}
