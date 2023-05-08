package com.synergies.synergy.domain.vo;

import org.springframework.web.multipart.MultipartFile;

public class AssignmentDetailsVo {
    private String refUserId;
    private int refAssignmentId;
    private String file;

    public AssignmentDetailsVo(String refUserId, int refAssignmentId, String file) {
        this.refUserId = refUserId;
        this.refAssignmentId = refAssignmentId;
        this.file = file;
    }

}
