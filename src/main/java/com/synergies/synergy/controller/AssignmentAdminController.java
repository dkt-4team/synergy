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
public class AssignmentAdminController {
    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/home")
    public String main(Model model) {
        List<NotificationDto> notiList = notificationService.notificationList();
        List<AssignmentDetail> assignmentList = assignmentService.getTodayAssignment();

        //System.out.println("hello");

        // 공지 데이터 불러오기
        if (notiList.isEmpty()){
            model.addAttribute("notiList", null);
        } else {
            model.addAttribute("notiList", notiList);
        }

        // 오늘 등록된 과제 데이터 불러오기
        if (assignmentList.isEmpty()) {
            model.addAttribute("assignmentList", null);
        }
        else {
            model.addAttribute("assignmentList", assignmentList);
        }

        model.addAttribute("AssignmentDTO", new AssignmentDto(assignmentList.size()));

        return "pages/admin/adminMain";
    }

    @PostMapping("/assignRegister")
    public String assignmentInsert(@ModelAttribute("AssignmentDTO") AssignmentDto assignment) {
        // 세션에 있는 ID가 교수님 ID가 아닐 때 권한이 없음

        assignmentService.insertAssignment(assignment);
        return "redirect:/admin/home";    // 관리자 페이지 메인 화면으로 이동
    }

    // 과제 확인하기
    @GetMapping("/assignmentDetail/{id}")
    public String assignmentDetails(@PathVariable("id") int assignmentId, Model model) {
        // 모든 과제들의 title 전송
        List<AssignmentDetail> assignmentList = assignmentService.assignmentList();
        if(assignmentList.isEmpty()) {
            model.addAttribute("assignmentList", null);
            model.addAttribute("assignmentDetail", null);
        } else {
            // 최근 과제의 상세 데이터 전송
            AssignmentContent assignDetail = assignmentService.assignmentDetails(assignmentId);
            model.addAttribute("assignmentList", assignmentList);
            model.addAttribute("assignmentDetail", assignDetail);
        }

        return "pages/admin/adminAssign";
    }

    @PostMapping("/assignmentDelete")
    public String assignmentRemove(@RequestParam("id") int assignmentId, Model model) {
        // TODO: result 값을 보내 화면에 알림창을 띄우도록 추가
        if (assignmentService.assignmentRemove(assignmentId)) {
            model.addAttribute("result", "success");
        } else {
            model.addAttribute("result", "fail");
        }
        return "redirect:/admin/home";
    }

    @GetMapping("/assignment")
    public String assignmentPage() {
        return "studentAssign";
    }

    @GetMapping("/admin/assignment")
    public String assignmentAdmin() {
        return "adminAssign";
    }

}
