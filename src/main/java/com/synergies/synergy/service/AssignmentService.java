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
    int insertAssignment(AssignmentDto assignment);
    int insertComment(CommentDto comment);
    int updateAssignment(AssignmentDto assignment);
    public ResponseEntity<byte[]> fileDownload(String fileUrl, boolean isManager);
    List<AssignmentDetail> getTodayAssignment();
    List<AssignmentDetail> assignmentList();
    AssignmentContent assignmentDetails(int assignmentId);
    AssignmentContent assignmentRecentDetails();
    List<SubmitStudent> submitStudentList(int assignmentId);
    List<UnsubmitStudent> unsubmitStudentList(int assignmentId);
    SubmitContent submitDetails(int submitId);
    List<CommentContent> commentDetails(int submitId);
    boolean assignmentRemove(int assignmentId);
    boolean commentRemove(int commentId);
    List<AssignmentVo> selectAllAssignment();
}
