package com.synergies.synergy.controller;

import com.synergies.synergy.domain.dto.AssignmentDetailsDto;
import com.synergies.synergy.domain.dto.AssignmentResponseDto.*;
import com.synergies.synergy.domain.dto.AssignmentResponseDto.AssignmentContent;
import com.synergies.synergy.domain.dto.AssignmentResponseDto.AssignmentDetail;
import com.synergies.synergy.domain.dto.AssignmentResponseDto.CommentContent;
import com.synergies.synergy.domain.vo.LoginUserInfoVo;
import com.synergies.synergy.service.AssignmentDetailsService;
import com.synergies.synergy.service.AssignmentService;
import com.synergies.synergy.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@SessionAttributes("loginUserInfo")
public class AssignmentController {
    @Autowired
    private AssignmentService assignmentService;
    @Autowired
    private AssignmentDetailsService assignmentDetailsService;

    // 과제 확인 페이지
    @GetMapping("/studentAssign/{id}")
    public String studentAssignDetail(@PathVariable("id") int assignmentId, Model model) {
        LoginUserInfoVo loginUserInfo = (LoginUserInfoVo)model.getAttribute("loginUserInfo");

        if(loginUserInfo == null || loginUserInfo.getUserId() == null){
            return "redirect:/";
        }

        // 모든 과제들의 title 전송
        List<AssignmentDetail> assignmentList = assignmentService.assignmentList();
        if(assignmentId == 0 || assignmentList.isEmpty()) {
            model.addAttribute("assignmentList", null);
            model.addAttribute("assignmentDetail", null);
        } else {

            model.addAttribute("assignmentList", assignmentList);

            // 선택한 과제의 상세 데이터 전송
            AssignmentContent assignDetail = assignmentService.assignmentDetails(assignmentId);
            model.addAttribute("assignmentDetail", assignDetail);

            // 해당 과제에 대한 교수자 코멘트
            GetComment getComment = new GetComment(assignDetail.getId(), loginUserInfo.getId());
            List<CommentContent> comment = assignmentService.commentStudent(getComment);
            System.out.println("***id : " + assignDetail.getId());
            System.out.println("***comment : " + comment);
            if(comment.isEmpty()) {
                model.addAttribute("comment", null);
            } else {
                model.addAttribute("comment", comment);
            }

        }

        return "pages/student/studentAssign";
    }

    @PostMapping("/studentAssignRegister")
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
        return "redirect:/studentAssign";
    }

    @GetMapping("/studentComment/{id}")
    public String getAssignmentDetail(@PathVariable int id, HttpSession session) {
        // 세션에 있는 ID가 교수님 ID가 아닐 때 권한이 없음
        AssignmentDetailsDto dto = new AssignmentDetailsDto(((LoginUserInfoVo) session.getAttribute("loginUserInfo")).getId(), id);
        assignmentDetailsService.getAssignmentDetail(dto);
        return "redirect:/studentAssign";    // 관리자 페이지 메인 화면으로 이동
    }
}
