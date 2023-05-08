package com.synergies.synergy.controller;

import com.synergies.synergy.domain.dto.AssignmentDto;
import com.synergies.synergy.domain.dto.AssignmentResponseDto.*;
import com.synergies.synergy.domain.dto.CommentDto;
import com.synergies.synergy.domain.dto.NotificationDto;
import com.synergies.synergy.domain.vo.LoginUserInfoVo;
import com.synergies.synergy.s3.FileDownloadService;
import com.synergies.synergy.s3.FileUploadService;
import com.synergies.synergy.service.AssignmentService;
import com.synergies.synergy.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(path="/admin")
public class AssignmentAdminController {
    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/home")
    public String main(Model model, HttpSession session) {
//        if(((LoginUserInfoVo) session.getAttribute("loginUserInfo")).getRole() != 0) {
//            return "redirect:/";
//        }
        List<NotificationDto> notiList = notificationService.notificationList();
        List<AssignmentDetail> assignmentList = assignmentService.getTodayAssignment();

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
    public String assignmentInsert(@ModelAttribute("AssignmentDTO") AssignmentDto assignment, HttpSession session) {
        // 세션에 있는 ID가 교수님 ID가 아닐 때 권한이 없음
//        if(((LoginUserInfoVo) session.getAttribute("loginUserInfo")).getRole() != 0) {
//            return "redirect:/";
//        }
        assignmentService.insertAssignment(assignment);
        return "redirect:/admin/home";    // 관리자 페이지 메인 화면으로 이동
    }

    @PostMapping("/assignmentUpdate")
    public String assignmentModify(@ModelAttribute("AssignmentDTO") AssignmentDto assignment, Model model, HttpSession session) {
        // 세션에 있는 ID가 교수님 ID가 아닐 때 권한이 없음
//        if(((LoginUserInfoVo) session.getAttribute("loginUserInfo")).getRole() != 0) {
//            return "redirect:/";
//        }

        // TODO : result 결과에 따른 알림창 띄우기
        if(assignmentService.updateAssignment(assignment) != 0) {
            model.addAttribute("result", "success");
        } else {
            model.addAttribute("result", "fail");
        }

        return "redirect:/admin/home";
    }

    // 과제 확인하기
    @GetMapping("/assignmentDetail/{id}")
    public String assignmentDetails(@PathVariable("id") int assignmentId, Model model, HttpSession session) {
//        if(((LoginUserInfoVo) session.getAttribute("loginUserInfo")).getRole() != 0) {
//            return "redirect:/";
//        }

        // 모든 과제들의 title 전송
        List<AssignmentDetail> assignmentList = assignmentService.assignmentList();

        // 선택한 과제를 제출한 학생 리스트
        List<SubmitStudent> submitStudents = assignmentService.submitStudentList(assignmentId);

        // 선택한 과제를 제출하지 않은 학생 리스트
        List<UnsubmitStudent> unsubmitStudents = assignmentService.unsubmitStudentList(assignmentId);

        model.addAttribute("submitStudents", submitStudents);
        model.addAttribute("unsubmitStudents", unsubmitStudents);

        if(assignmentList.isEmpty()) {
            model.addAttribute("assignmentList", null);
            model.addAttribute("assignmentDetail", null);
        } else {
            AssignmentContent assignDetail = assignmentService.assignmentDetails(assignmentId);
            model.addAttribute("assignmentList", assignmentList);
            // 선택한 과제의 상세 데이터 전송
            model.addAttribute("assignmentDetail", assignDetail);
        }

        model.addAttribute("AssignmentDTO", new AssignmentDto(assignmentList.size()));

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

    // 학생이 제출한 과제 상세 페이지
    @GetMapping("/assignmentSubmit/{id}")
    public String assignmentSubmit(@PathVariable("id") int submitId, Model model, HttpSession session) {
//        if(((LoginUserInfoVo) session.getAttribute("loginUserInfo")).getRole() != 0) {
//            return "redirect:/";
//        }

        // 학생이 제출한 과제 데이터
        SubmitContent submitContent = assignmentService.submitDetails(submitId);
        model.addAttribute("submit", submitContent);

        // 과제에 대한 코멘트
        List<CommentContent> comment = assignmentService.commentDetails(submitId);
        if(comment.size() == 0) {
            model.addAttribute("comment", null);
        } else {
            model.addAttribute("comment", comment);
        }

        // 교수님 코멘트 데이터 받아올 빈 객체 전송
        model.addAttribute("CommentDTO", new CommentDto(submitId));

        return "pages/admin/adminAssignDetail";
    }

    @PostMapping("/commentSave")
    public String commentSave(@ModelAttribute("CommentDTO") CommentDto comment, HttpSession session) {
        //        if(((LoginUserInfoVo) session.getAttribute("loginUserInfo")).getRole() != 0) {
//            return "redirect:/";
//        }
        System.out.println("***commentId : " + comment.getSubmitId());
        assignmentService.insertComment(comment);       // TODO : 예외처리 추가
        return "redirect:/admin/assignmentSubmit/"+comment.getSubmitId();
    }

    @PostMapping("/commentDelete")
    public String commentRemove(@RequestParam("id") int commentId, int submitId, Model model, HttpSession session) {
        //        if(((LoginUserInfoVo) session.getAttribute("loginUserInfo")).getRole() != 0) {
//            return "redirect:/";
//        }
        // TODO: result 값을 보내 화면에 알림창을 띄우도록 추가
        if (assignmentService.commentRemove(commentId)) {
            model.addAttribute("result", "success");
        } else {
            model.addAttribute("result", "fail");
        }
        return "redirect:/admin/assignmentSubmit/"+submitId;
    }

    @GetMapping("/assignmentDownload")
    public ResponseEntity<byte[]> assignmentDownload(Model model, HttpSession session) throws IOException {
        //@RequestParam("file") String fileUrl,
        String fileUrl="230503_10";

        return assignmentService.fileDownload(fileUrl, true);
    }

}
