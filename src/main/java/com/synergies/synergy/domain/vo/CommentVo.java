package com.synergies.synergy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class CommentVo {
    private int id;
    private int refAssignmentSubmitId;
    private String content;
    private Date regDate;

    public CommentVo(int refAssignmentSubmitId, String content) {
        this.refAssignmentSubmitId = refAssignmentSubmitId;
        this.content = content;
    }
}
