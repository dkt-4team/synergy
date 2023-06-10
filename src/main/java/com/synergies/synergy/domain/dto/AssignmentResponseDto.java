package com.synergies.synergy.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class AssignmentResponseDto {

    @Getter
    @Setter
    @ToString
    public static class AssignmentDetail {
        private int id;
        private String title;
    }

    @Getter
    @Setter
    @ToString
    public static class AssignmentContent {
        private int id;
        private String title;
        private String content;
        private String assignmentFile;
    }

    @Getter
    public static class SubmitStudent {
        private String name;
        private int id;
    }

    @Getter
    @ToString
    public static class UnsubmitStudent {
        private String name;
    }

    @Getter
    @Setter
    @ToString
    public static class SubmitContent {
        private int id;
        private String name;
        private String submitFile;
        private String regDate;
    }

    @Getter
    @ToString
    public static class CommentContent {
        private int id;
        private String content;
    }

    @Setter
    @AllArgsConstructor
    public static class GetComment {
        private int assignmentId;
        private byte[] userId;
    }
}
