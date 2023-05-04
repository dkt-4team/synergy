package com.synergies.synergy.service;

import com.synergies.synergy.dao.AssignmentDao;
import com.synergies.synergy.domain.dto.AssignmentDto;
import com.synergies.synergy.domain.vo.AssignmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AssignmentServiceImpl implements AssignmentService {
    @Autowired
    private AssignmentDao assignmentDao;

    @Override
    public int insertAssignment(AssignmentDto assignment) {
        String file = "";
        AssignmentVo vo = new AssignmentVo(assignment.getTitle(), assignment.getContent(), file);
        return assignmentDao.insertAssignment(vo);
    }


    @Override
    public List<AssignmentVo> selectAllAssignment() {
        return assignmentDao.selectAllAssignment();
    }

    @Override
    public List<AssignmentVo> getTodayAssignment() {
        List<AssignmentVo> assignment = assignmentDao.getTodayAssignment();
        System.out.println(assignment);
        return assignment;

    }
}
