package com.synergies.synergy.service;

import com.synergies.synergy.dao.AssignmentDetailsDao;
import com.synergies.synergy.domain.dto.AssignmentDetailsDto;
import com.synergies.synergy.domain.vo.AssignmentDetailsVo;
import com.synergies.synergy.domain.vo.AssignmentVo;
import com.synergies.synergy.s3.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AssignmentDeatilsServiceImpl implements AssignmentDetailsService{
    @Autowired
    private AssignmentDetailsDao assignmentDetailsDao;

    @Autowired
    private FileUploadService fileUpload;

    @Override
    public List<AssignmentDetailsDto> getAssignmentDetail(AssignmentDetailsDto assignment){
        return assignmentDetailsDao.getAssignmentDetail(assignment);
    }

    @Override
    public int insertAssignmentDetail(AssignmentDetailsDto assignment){
        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
        String fileName = simpleDateFormat.format(nowDate)+"_"+(assignment.getRefAssignmentId()+1);
        fileUpload.uploadFile(fileName, false, assignment.getFile());
        AssignmentDetailsVo vo = new AssignmentDetailsVo(assignment.getRefUserId(), assignment.getRefAssignmentId(), fileName);

        return assignmentDetailsDao.insertAssignmentDetail(vo);
    }
}
