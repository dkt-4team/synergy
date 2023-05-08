package com.synergies.synergy.domain.vo;

import lombok.Getter;

import java.util.Date;

@Getter
public class AssignmentSubmitVo {
    private int id;
    private byte[] refUserId;
    private int refAssignmentId;
    private String submitFile;
    private Date regDate;
}
