package com.synergies.synergy.service;

import com.synergies.synergy.dao.AssignmentDetailsDao;
import com.synergies.synergy.domain.dto.AssignmentDetailsDto;
import com.synergies.synergy.domain.vo.AssignmentDetailsVo;
import com.synergies.synergy.s3.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AssignmentDeatilsServiceImpl implements AssignmentDetailsService {
    @Autowired
    private AssignmentDetailsDao assignmentDetailsDao;

    @Autowired
    private FileUploadService fileUpload;

    @Override
    public List<AssignmentDetailsDto> readAssignmentDetail(AssignmentDetailsDto assignment) {
        return assignmentDetailsDao.selectAssignmentDetail(assignment);
    }

    @Override
    public int createAssignmentDetail(int assignmentId, AssignmentDetailsDto assignment){
        String fileName = assignmentId + "/" + UUID.randomUUID();
        fileUpload.uploadFile(fileName, false, assignment.getFile());
        AssignmentDetailsVo vo = new AssignmentDetailsVo(assignment.getRefUserId(), assignment.getRefAssignmentId(), fileName);

        return assignmentDetailsDao.insertAssignmentDetail(vo);
    }
}
