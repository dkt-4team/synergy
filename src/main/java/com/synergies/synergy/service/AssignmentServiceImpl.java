package com.synergies.synergy.service;

import com.synergies.synergy.dao.AssignmentDao;
import com.synergies.synergy.domain.dto.AssignmentDto;
import com.synergies.synergy.domain.dto.AssignmentResponseDto.*;
import com.synergies.synergy.domain.dto.CommentDto;
import com.synergies.synergy.domain.vo.AssignmentVo;
import com.synergies.synergy.domain.vo.CommentVo;
import com.synergies.synergy.s3.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {
    @Autowired
    private AssignmentDao assignmentDao;

    @Autowired
    private FileUploadService fileUpload;

    @Override
    public int createAssignment(AssignmentDto assignment) {
        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
        String fileName = simpleDateFormat.format(nowDate) + "_" + (assignment.getAssignmentNumber() + 1);
        fileUpload.uploadFile(fileName, true, assignment.getFile());
        AssignmentVo vo = new AssignmentVo(assignment.getTitle(), assignment.getContent(), fileName);

        return assignmentDao.insertAssignment(vo);
    }

    @Override
    public int createComment(CommentDto comment) {
        CommentVo commentVo = new CommentVo(comment.getSubmitId(), comment.getContent());
        return assignmentDao.insertComment(commentVo);
    }

    @Override
    public int updateAssignment(AssignmentDto assignment) {
        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
        String fileName = simpleDateFormat.format(nowDate) + "_" + (assignment.getAssignmentNumber() + 1);
        fileUpload.uploadFile(fileName, true, assignment.getFile());

        AssignmentVo assignmentVo =
                new AssignmentVo(assignment.getId(), assignment.getTitle(), assignment.getContent(), fileName);

        return assignmentDao.updateAssignment(assignmentVo);
    }

    // 오늘 등록한 과제 리스트 가져오기
    @Override
    public List<AssignmentDetail> readTodayAssignment() {
        return assignmentDao.selectTodayAssignment();
    }

    // 모든 과제의 title 가져오기
    @Override
    public List<AssignmentDetail> readAssignmentList() {
        return assignmentDao.selectAllAssignmentTitle();
    }


    @Override
    public List<AssignmentVo> readAllAssignment() {
        return assignmentDao.selectAllAssignment();
    }

    @Override
    public AssignmentContent readAssignmentDetails(int assignmentId) {
        AssignmentContent assignment = assignmentDao.selectAssignmentDetails(assignmentId);
        assignment.setAssignmentFile(fileUpload.getUrl() + "/admin/" + assignment.getAssignmentFile());
        return assignment;
    }

    @Override
    public AssignmentContent readAssignmentRecentDetails() {
        return assignmentDao.selectRecentAssignment();
    }

    @Override
    public List<SubmitStudent> readSubmitStudentList(int assignmentId) {
        return assignmentDao.selectSubmitStudents(assignmentId);
    }

    @Override
    public List<UnsubmitStudent> readUnsubmitStudentList(int assignmentId) {
        return assignmentDao.selectUnsubmitStudents(assignmentId);
    }

    @Override
    public SubmitContent readSubmitDetails(int submitId) {
        SubmitContent submit = assignmentDao.selectSubmitContent(submitId);
        submit.setSubmitFile(fileUpload.getUrl() + "/student/" + submit.getSubmitFile());

        return submit;
    }

    @Override
    public List<CommentContent> readCommentDetails(int submitId) {
        return assignmentDao.selectComment(submitId);
    }

    @Override
    public List<CommentContent> readCommentStudent(GetComment getComment) {
        return assignmentDao.selectCommentStudent(getComment);
    }

    @Override
    public boolean deleteAssignment(int assignmentId) {
        AssignmentContent assignment = assignmentDao.selectAssignmentDetails(assignmentId);
        fileUpload.deleteObject(true, assignment.getAssignmentFile());
        return assignmentDao.deleteAssignment(assignmentId);
    }

    @Override
    public boolean deleteComment(int commentId) {
        return assignmentDao.deleteComment(commentId);
    }
}
