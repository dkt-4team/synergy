package com.synergies.synergy.controller;

import com.synergies.synergy.domain.dto.AssignmentDto;
import com.synergies.synergy.domain.dto.AssignmentResponseDto.*;
import com.synergies.synergy.domain.dto.NotificationDto;
import com.synergies.synergy.domain.vo.AssignmentVo;
import com.synergies.synergy.domain.vo.LoginUserInfoVo;
import com.synergies.synergy.service.AssignmentService;
import com.synergies.synergy.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/admin")
@SessionAttributes("loginUserInfo")
public class AssignmentController {
    @Autowired
    private AssignmentService assignmentService;

    @GetMapping("/studentAssign")
    public String studentAssignPage(Model model) {
        LoginUserInfoVo loginUserInfo = (LoginUserInfoVo)model.getAttribute("loginUserInfo");
        if(loginUserInfo == null || loginUserInfo.getUserId() == null){
            return "redirect:/";
        }

        List<AssignmentDetail> assignList = assignmentService.assignmentList();

        if (assignList.isEmpty()) {
            model.addAttribute("assignList", null);
            return "pages/student/studentAssign";
        }
        model.addAttribute("assignList", assignList);

        return "pages/student/studentAssign";
    }

}
