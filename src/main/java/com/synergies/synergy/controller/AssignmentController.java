package com.synergies.synergy.controller;

import com.synergies.synergy.domain.dto.AssignmentDetailsDto;
import com.synergies.synergy.domain.dto.AssignmentResponseDto.AssignmentContent;
import com.synergies.synergy.domain.dto.AssignmentResponseDto.AssignmentDetail;
import com.synergies.synergy.domain.dto.AssignmentResponseDto.CommentContent;
import com.synergies.synergy.domain.dto.AssignmentResponseDto.GetComment;
import com.synergies.synergy.domain.vo.LoginUserInfoVo;
import com.synergies.synergy.service.AssignmentDetailsService;
import com.synergies.synergy.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/student")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private AssignmentDetailsService assignmentDetailsService;

    // 과제 확인 페이지
    @GetMapping("/assign/{id}")
    public String studentAssignDetail(@PathVariable("id") int assignmentId, Model model, HttpSession session) {

        // 모든 과제들의 title 전송
        List<AssignmentDetail> assignmentList = assignmentService.assignmentList();
        if (assignmentId == 0 || assignmentList.isEmpty()) {
            model.addAttribute("assignmentList", null);
            model.addAttribute("assignmentDetail", null);
        } else {
            model.addAttribute("assignmentList", assignmentList);

            // 선택한 과제의 상세 데이터 전송
            AssignmentContent assignDetail = assignmentService.assignmentDetails(assignmentId);
            model.addAttribute("assignmentDetail", assignDetail);

            // 해당 과제에 대한 교수자 코멘트
            GetComment getComment = new GetComment(assignDetail.getId(), ((LoginUserInfoVo) session.getAttribute("loginUserInfo")).getId());
            List<CommentContent> comment = assignmentService.commentStudent(getComment);

            if(comment.isEmpty()) {
                model.addAttribute("comment", null);
            } else {
                model.addAttribute("comment", comment);
            }

            // 과제 제출에 필요한 빈 객체 전송
            model.addAttribute("AssignmentDetailsDto", new AssignmentDetailsDto(assignmentId));
        }

        return "pages/student/studentAssign";
    }

    @PostMapping("/assignRegister")
    public String assignmentInsert(@ModelAttribute("AssignmentDetailsDto") AssignmentDetailsDto assignment, HttpSession session, RedirectAttributes redirectAttributes) {

        assignment.setRefUserId(((LoginUserInfoVo) session.getAttribute("loginUserInfo")).getId());

        String message;
        if(assignment.getFile().isEmpty()){
            message ="파일이 비어있습니다. 선택 후 제출해주세요!";

        }else {
            assignmentDetailsService.insertAssignmentDetail(assignment);
            message ="제출에 성공했습니다.";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/student/assign/" + assignment.getRefAssignmentId();
    }
}
