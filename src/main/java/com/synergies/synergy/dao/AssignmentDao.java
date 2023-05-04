package com.synergies.synergy.dao;

import com.synergies.synergy.domain.vo.AssignmentVo;
import com.synergies.synergy.domain.dto.AssignmentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AssignmentDao {
    int insertAssignment(AssignmentVo assignment);
    List<AssignmentVo> getTodayAssignment();
    List<AssignmentDto> selectAllAssignment();

}
