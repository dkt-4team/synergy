package com.synergies.synergy.service;

import com.synergies.synergy.domain.dto.AssignmentDto;
import com.synergies.synergy.domain.vo.AssignmentVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AssignmentService {
    int insertAssignment(AssignmentDto assignment);
    List<AssignmentVo> getTodayAssignment();
    List<AssignmentDto> selectAllAssignment();
}
