package com.synergies.synergy.service;

import com.synergies.synergy.domain.dto.AssignmentDetailsDto;
import com.synergies.synergy.domain.dto.AssignmentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AssignmentDetailsService {
    List<AssignmentDetailsDto> getAssignmentDetail(AssignmentDetailsDto assignment);
    int insertAssignmentDetail(AssignmentDetailsDto assignment);
}
