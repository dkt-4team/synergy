package com.synergies.synergy.service;

import com.synergies.synergy.domain.dto.AssignmentDto;
import com.synergies.synergy.domain.dto.AssignmentResponseDto.*;
import com.synergies.synergy.domain.dto.CommentDto;
import com.synergies.synergy.domain.vo.AssignmentVo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AssignmentService {
    int createAssignment(AssignmentDto assignment);

    int createComment(CommentDto comment);

    int updateAssignment(AssignmentDto assignment);

    public ResponseEntity<byte[]> fileDownload(String fileUrl, boolean isManager);

    List<AssignmentDetail> readTodayAssignment();

    List<AssignmentDetail> readAssignmentList();

    AssignmentContent readAssignmentDetails(int assignmentId);

    AssignmentContent readAssignmentRecentDetails();

    List<SubmitStudent> readSubmitStudentList(int assignmentId);

    List<UnsubmitStudent> readUnsubmitStudentList(int assignmentId);

    SubmitContent readSubmitDetails(int submitId);

    List<CommentContent> readCommentDetails(int submitId);

    List<CommentContent> readCommentStudent(GetComment getComment);

    boolean deleteAssignment(int assignmentId);

    boolean deleteComment(int commentId);

    List<AssignmentVo> readAllAssignment();
}
