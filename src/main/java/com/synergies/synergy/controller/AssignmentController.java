package com.synergies.synergy.controller;

import com.synergies.synergy.domain.dto.AssignmentDto;
import com.synergies.synergy.domain.vo.AssignmentVo;
import com.synergies.synergy.service.AssignmentService;
import com.synergies.synergy.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class AssignmentController {
    @Autowired
    private AssignmentService assignmentService;

    @PostMapping("/assignment/insert")
    public String assignmentInsert(@ModelAttribute("AssignmentDTO") AssignmentDto assignment) {
        // 세션에 있는 ID가 교수님 ID가 아닐 때 권한이 없음


        assignmentService.insertAssignment(assignment);
        return "redirect:/adminMain";    // 관리자 페이지 메인 화면으로 이동
    }

    @GetMapping("/adminMain")
    public String main(Model model) {
        List<AssignmentVo> assigmentList = assignmentService.getTodayAssignment();

        if (assigmentList.isEmpty()) {
            model.addAttribute("assignmentList", null);
        }
        else {
            model.addAttribute("assignmentList", assigmentList);
        }
        model.addAttribute("AssignmentDTO", new AssignmentDto());

        return "adminMain";
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
