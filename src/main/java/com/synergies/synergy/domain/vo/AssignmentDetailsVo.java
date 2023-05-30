package com.synergies.synergy.domain.vo;

import lombok.Getter;

@Getter
public class AssignmentDetailsVo {
    private byte[] refUserId;
    private int refAssignmentId;
    private String file;

    public AssignmentDetailsVo(byte[] refUserId, int refAssignmentId, String file) {
        this.refUserId = refUserId;
        this.refAssignmentId = refAssignmentId;
        this.file = file;
    }

}
