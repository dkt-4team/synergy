package com.synergies.synergy.service;

import com.synergies.synergy.dao.AssignmentDao;
import com.synergies.synergy.domain.dto.AssignmentDto;
import com.synergies.synergy.domain.dto.AssignmentResponseDto.*;
import com.synergies.synergy.domain.dto.CommentDto;
import com.synergies.synergy.domain.vo.AssignmentVo;
import com.synergies.synergy.domain.vo.CommentVo;
import com.synergies.synergy.s3.FileDownloadService;
import com.synergies.synergy.s3.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {
    @Autowired
    private AssignmentDao assignmentDao;

    @Autowired
    private FileUploadService fileUpload;

    @Autowired
    private FileDownloadService fileDownload;

    @Override
    public ResponseEntity<byte[]> fileDownload(String fileUrl, boolean isManager) {
        String filePath;
        if (isManager) {
            filePath = "https://synergy-file.s3.ap-northeast-2.amazonaws.com/synergy-file/admin/" + fileUrl;
        } else {
            filePath = "https://synergy-file.s3.ap-northeast-2.amazonaws.com/synergy-file/student/" + fileUrl;
        }

        System.out.println("***file Download : " + filePath);
        System.out.println("***file Download : " + filePath.substring(52));

        try {
            return fileDownload.downloadFile(filePath.substring(52));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
        return assignmentDao.selectAssignmentDetails(assignmentId);
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
        return assignmentDao.selectSubmitContent(submitId);
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
        return assignmentDao.deleteAssignment(assignmentId);
    }

    @Override
    public boolean deleteComment(int commentId) {
        return assignmentDao.deleteComment(commentId);
    }
}
