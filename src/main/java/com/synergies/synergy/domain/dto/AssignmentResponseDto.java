package com.synergies.synergy.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class AssignmentResponseDto {

    @Getter
    @Setter
    public static class AssignmentDetail {
        private int id;
        private String title;
    }

    @Getter
    @Setter
    public static class AssignmentContent {
        private int id;
        private String title;
        private String content;
        private String file;
    }

    @Getter
    public static class SubmitStudent {
        private String name;
        private int id;
    }

    @Getter
    public static class UnsubmitStudent {
        private String name;
    }
}
