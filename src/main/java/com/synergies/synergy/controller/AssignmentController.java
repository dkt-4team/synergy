package com.synergies.synergy.controller;

import com.synergies.synergy.domain.dto.AssignmentDto;
import com.synergies.synergy.domain.vo.AssignmentVo;
import com.synergies.synergy.domain.vo.LoginUserInfoVo;
import com.synergies.synergy.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("loginUserInfo")
public class AssignmentController {
    @Autowired
    private AssignmentService assignmentService;

    @PostMapping("/assignment/insert")
    public String assignmentInsert(@ModelAttribute("AssignmentDTO") AssignmentDto assignment) {
        // 세션에 있는 ID가 교수님 ID가 아닐 때 권한이 없음
        assignmentService.insertAssignment(assignment);
        return "redirect:/adminMain";    // 관리자 페이지 메인 화면으로 이동
    }



    @GetMapping("/studentAssign")
    public String studentAssignPage(Model model) {
        LoginUserInfoVo loginUserInfo = (LoginUserInfoVo)model.getAttribute("loginUserInfo");
        if(loginUserInfo == null || loginUserInfo.getUserId() == null){
            return "redirect:/";
        }

        List<AssignmentDto> assignList = assignmentService.selectAllAssignment();

        if (assignList.isEmpty()) {
            model.addAttribute("assignList", null);
            return "pages/student/studentAssign";
        }
        model.addAttribute("assignList", assignList);

        return "pages/student/studentAssign";
    }

    @GetMapping("/assignment")
    public String assignmentPage() {
        return "studentAssign";
    }

    @GetMapping("/admin/assignment")
    public String assignmentAdmin() {
        return "adminAssign";
    }

    @GetMapping("/assignmentDetail")
    public String assignmentAdmin2() {
        return "adminAssignDetail";
    }
}
