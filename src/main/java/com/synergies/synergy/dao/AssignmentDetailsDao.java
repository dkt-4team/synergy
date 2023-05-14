package com.synergies.synergy.dao;

import com.synergies.synergy.domain.dto.AssignmentDetailsDto;
import com.synergies.synergy.domain.vo.AssignmentDetailsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AssignmentDetailsDao {
    List<AssignmentDetailsDto> selectAssignmentDetail(AssignmentDetailsDto assignment);

    int insertAssignmentDetail(AssignmentDetailsVo assignment);
}


