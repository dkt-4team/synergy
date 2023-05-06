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
@SessionAttributes("loginUserInfo")
public class AssignmentController {
    @Autowired
    private AssignmentService assignmentService;

    // 과제 확인 페이지
    @GetMapping("/studentAssign/{id}")
    public String studentAssignDetail(@PathVariable("id") int assignmentId, Model model) {
        LoginUserInfoVo loginUserInfo = (LoginUserInfoVo)model.getAttribute("loginUserInfo");
        if(loginUserInfo == null || loginUserInfo.getUserId() == null){
            return "redirect:/";
        }

//        List<AssignmentDetail> assignList = assignmentService.assignmentList();
//
//        if (assignList.isEmpty()) {
//            model.addAttribute("assignList", null);
//            return "pages/student/studentAssign";
//        }
//        model.addAttribute("assignList", assignList);

        // 모든 과제들의 title 전송
        List<AssignmentDetail> assignmentList = assignmentService.assignmentList();
        if(assignmentList.isEmpty()) {
            model.addAttribute("assignmentList", null);
            model.addAttribute("assignmentDetail", null);
        } else {
            AssignmentContent assignDetail = assignmentService.assignmentDetails(assignmentId);
            model.addAttribute("assignmentList", assignmentList);
            // 선택한 과제의 상세 데이터 전송
            model.addAttribute("assignmentDetail", assignDetail);
        }

        return "pages/student/studentAssign";
    }

    @GetMapping("/studentAssign")
    public String studentAssignPage(Model model) {
        LoginUserInfoVo loginUserInfo = (LoginUserInfoVo)model.getAttribute("loginUserInfo");
        if(loginUserInfo == null || loginUserInfo.getUserId() == null){
            return "redirect:/";
        }

        // 모든 과제들의 title 전송
        List<AssignmentDetail> assignmentList = assignmentService.assignmentList();

        // 최근 과제 정보 전송
        AssignmentContent recentAssign = assignmentService.assignmentRecentDetails();
        System.out.println("***" + recentAssign.getTitle());
        System.out.println("***" + recentAssign.getContent());

        if(assignmentList.isEmpty()) {
            model.addAttribute("assignmentList", null);
            model.addAttribute("assignmentDetail", null);
        } else {
            model.addAttribute("assignmentList", assignmentList);
            // 선택한 과제의 상세 데이터 전송
            model.addAttribute("assignmentDetail", recentAssign);
        }

        return "pages/student/studentAssign";
    }

    // 과제 제출하기
    @PostMapping("/assignmentSave")
    public String assignmentSubmit(Model model) {
        return "pages/student/studentAssign";
    }

}
