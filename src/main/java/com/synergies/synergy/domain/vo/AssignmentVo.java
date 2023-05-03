package com.synergies.synergy.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.joda.time.DateTime;

import java.util.Date;

@Getter
@AllArgsConstructor
public class AssignmentVo {
    private int id;
    private String title;
    private String content;
    private String assignmentFile;
    private Date regDate;

    public AssignmentVo(String title, String content, String assignmentFile) {
        this.title = title;
        this.content = content;
        this.assignmentFile = assignmentFile;
    }

    public AssignmentVo(int id, String title) {
        this.id = id;
        this.title = title;
    }
}
