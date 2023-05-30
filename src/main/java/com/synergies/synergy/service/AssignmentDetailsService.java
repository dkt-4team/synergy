package com.synergies.synergy.service;

import com.synergies.synergy.domain.dto.AssignmentDetailsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AssignmentDetailsService {
    List<AssignmentDetailsDto> readAssignmentDetail(AssignmentDetailsDto assignment);

    int createAssignmentDetail(AssignmentDetailsDto assignment);
}
