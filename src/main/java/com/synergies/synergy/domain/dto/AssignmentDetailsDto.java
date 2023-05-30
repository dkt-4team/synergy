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
    private byte[] refUserId;
    private int refAssignmentId;
    private MultipartFile file;
    private String comment;
    private String regDate;

    public AssignmentDetailsDto(byte[] refUserId, int refAssignmentId, MultipartFile file) {
        this.refUserId = refUserId;
        this.refAssignmentId = refAssignmentId;
        this.file = file;
    }

    public AssignmentDetailsDto(byte[] refUserId, int refAssignmentId) {
        this.refUserId = refUserId;
        this.refAssignmentId = refAssignmentId;
    }

    public AssignmentDetailsDto(int refAssignmentId) {
        this.refAssignmentId = refAssignmentId;
    }
}
