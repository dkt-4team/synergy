package com.synergies.synergy.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentDetailsDto {
    private String refUserId;
    private int refAssignmentId;
    private MultipartFile file;
    private String comment;
    private String regDate;

    public AssignmentDetailsDto(String refUserId, int refAssignmentId, MultipartFile file) {
        this.refUserId = refUserId;
        this.refAssignmentId = refAssignmentId;
        this.file = file;
    }

    public AssignmentDetailsDto(String refUserId, int refAssignmentId) {
        this.refUserId = refUserId;
        this.refAssignmentId = refAssignmentId;
    }

}
