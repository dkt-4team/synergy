package com.synergies.synergy.service;

import com.synergies.synergy.dao.AssignmentDao;
import com.synergies.synergy.domain.dto.AssignmentDto;
import com.synergies.synergy.domain.dto.AssignmentResponseDto.*;
import com.synergies.synergy.domain.vo.AssignmentVo;
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
    public int insertAssignment(AssignmentDto assignment) {
        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
        String fileName = simpleDateFormat.format(nowDate)+"_"+(assignment.getAssignmentNumber()+1);
        fileUpload.uploadFile(fileName, true, assignment.getFile());
        AssignmentVo vo = new AssignmentVo(assignment.getTitle(), assignment.getContent(), fileName);

        return assignmentDao.insertAssignment(vo);
    }

    // 오늘 등록한 과제 리스트 가져오기
    @Override
    public List<AssignmentDetail> getTodayAssignment() {
        return assignmentDao.getTodayAssignment();
    }

    // 모든 과제의 title 가져오기
    @Override
    public List<AssignmentDetail> assignmentList() {
        return assignmentDao.selectAllAssignmentTitle();
    }


    @Override
    public List<AssignmentVo> selectAllAssignment() {
        return assignmentDao.selectAllAssignment();
    }

    @Override
    public AssignmentContent assignmentDetails(int assignmentId) {
        return assignmentDao.selectAssignmentDetails(assignmentId);
    }

    @Override
    public AssignmentContent assignmentRecentDetails() {
        return assignmentDao.selectRecentAssignment();
    }

    @Override
    public List<SubmitStudent> submitStudentList(int assignmentId) {
        return assignmentDao.selectSubmitStudents(assignmentId);
    }

    @Override
    public List<UnsubmitStudent> unsubmitStudentList(int assignmentId) {
        return assignmentDao.selectUnsubmitStudents(assignmentId);
    }

    @Override
    public SubmitContent submitDetails(int submitId) {
        return assignmentDao.selectSubmitContent(submitId);
    }

    @Override
    public List<CommentContent> commentDetails(int submitId) {
        return assignmentDao.selectComment(submitId);
    }

    @Override
    public boolean assignmentRemove(int assignmentId) {
        return assignmentDao.deleteAssignment(assignmentId);
    }
}
