package com.synergies.synergy.controller;

import com.synergies.synergy.domain.dto.AssignmentDto;
import com.synergies.synergy.domain.dto.AssignmentResponseDto;
import com.synergies.synergy.domain.dto.AssignmentResponseDto.*;
import com.synergies.synergy.domain.dto.CommentDto;
import com.synergies.synergy.domain.dto.NotificationDto;
import com.synergies.synergy.service.AssignmentService;
import com.synergies.synergy.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping(path = "/admin")
public class AssignmentAdminController {
    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/home")
    public String main(Model model) {
        List<NotificationDto> notiList = notificationService.readNotificationList();
        List<AssignmentDetail> assignmentList = assignmentService.readTodayAssignment();

        log.info("[관리자 메인페이지 과제 리스트 확인]\n" + assignmentList.stream().map(i -> i.toString()).collect(Collectors.joining("\n")));
        log.info("[관리자 메인페이지 과제 리스트 확인]\n" + notiList.stream().map(i -> i.toString()).collect(Collectors.joining("\n")));
        // 공지 데이터 불러오기
        if (notiList.isEmpty()) {
            model.addAttribute("notiList", null);
        } else {
            model.addAttribute("notiList", notiList);
        }

        // 오늘 등록된 과제 데이터 불러오기
        if (assignmentList.isEmpty()) {
            model.addAttribute("assignmentList", null);
        } else {
            model.addAttribute("assignmentList", assignmentList);
        }

        model.addAttribute("AssignmentDTO", new AssignmentDto(assignmentList.size()));

        // 최근 과제의 ID 값
        AssignmentResponseDto.AssignmentContent assignment = assignmentService.readAssignmentRecentDetails();
        if (assignment == null) {
            model.addAttribute("assignId", 0);
        } else {
            model.addAttribute("assignId", assignment.getId());
        }

        return "pages/admin/adminMain";
    }

    @PostMapping("/assignRegister")
    public String assignmentInsert(@ModelAttribute("AssignmentDTO") AssignmentDto assignment) {
        log.info("관리자가 등록할 과제 데이터 확인(insert) : " + assignment);
        assignmentService.createAssignment(assignment);

        return "redirect:/admin/home";    // 관리자 페이지 메인 화면으로 이동
    }

    @PostMapping("/assignmentUpdate/{id}")
    public String assignmentModify(@PathVariable int id, @ModelAttribute("AssignmentDTO") AssignmentDto assignment, Model model, RedirectAttributes redirectAttributes) {

        assignment.setId(id);
        log.info("관리자가 수정한 과제 데이터 확인 : " + assignment.getId() + " " + assignment);
        if (assignmentService.updateAssignment(assignment) != 0) {
            model.addAttribute("result", "success");
        } else {
            model.addAttribute("result", "fail");
        }
        log.info("관리자 과제 수정 성공 여부 : " + model.getAttribute("result"));
        String message = "과제를 수정하셨습니다!";
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:/admin/assignmentDetail/" + id;
    }

    // 과제 확인하기
    @GetMapping("/assignmentDetail/{id}")
    public String assignmentDetails(@PathVariable("id") int assignmentId, Model model) {

        // 모든 과제들의 title 전송
        List<AssignmentDetail> assignmentList = assignmentService.readAssignmentList();

        // 선택한 과제를 제출한 학생 리스트
        List<SubmitStudent> submitStudents = assignmentService.readSubmitStudentList(assignmentId);

        // 선택한 과제를 제출하지 않은 학생 리스트
        List<UnsubmitStudent> unsubmitStudents = assignmentService.readUnsubmitStudentList(assignmentId);

        log.info(String.format("[관리자 과제 상세 확인] 과제id(%d) - 모든 과제 개수 확인 : %d,"
                        + "  선택한 과제 제출한 학생 명수 확인 : %d,"
                        + " 과제 제출하지 않은 학생 확인 : %d"
                , assignmentId, assignmentList.size(), submitStudents.size(), unsubmitStudents.size()));

        model.addAttribute("submitStudents", submitStudents);
        model.addAttribute("unsubmitStudents", unsubmitStudents);

        if (assignmentId == 0 || assignmentList.isEmpty()) {
            model.addAttribute("assignmentList", null);
            model.addAttribute("assignmentDetail", null);
        } else {
            AssignmentContent assignDetail = assignmentService.readAssignmentDetails(assignmentId);
            model.addAttribute("assignmentList", assignmentList);
            // 선택한 과제의 상세 데이터 전송
            model.addAttribute("assignmentDetail", assignDetail);
        }

        model.addAttribute("AssignmentDTO", new AssignmentDto(assignmentList.size()));

        return "pages/admin/adminAssign";
    }

    @PostMapping("/assignmentDelete")
    public String assignmentRemove(@RequestParam("id") int assignmentId, RedirectAttributes redirectAttributes) {
        // TODO: result 값을 보내 화면에 알림창을 띄우도록 추가
        String message;
        if (assignmentService.deleteAssignment(assignmentId)) {
            message = "과제를 삭제하셨습니다!";
            redirectAttributes.addFlashAttribute("message", message);
        } else {
            message = "과제 삭제에 실패하셨습니다!";
            redirectAttributes.addFlashAttribute("message", message);
        }
        log.info("관리자 과제 삭제 확인 : " + message);
        return "redirect:/admin/home";
    }

    // 학생이 제출한 과제 상세 페이지
    @GetMapping("/assignmentSubmit/{id}")
    public String assignmentSubmit(@PathVariable("id") int submitId, Model model) {

        // 학생이 제출한 과제 데이터
        SubmitContent submitContent = assignmentService.readSubmitDetails(submitId);
        model.addAttribute("submit", submitContent);

        // 과제에 대한 코멘트
        List<CommentContent> comment = assignmentService.readCommentDetails(submitId);
        if (comment.size() == 0) {
            model.addAttribute("comment", null);
        } else {
            model.addAttribute("comment", comment);
        }

        // 교수님 코멘트 데이터 받아올 빈 객체 전송
        model.addAttribute("CommentDTO", new CommentDto(submitId));

        return "pages/admin/adminAssignDetail";
    }

    @PostMapping("/commentSave")
    public String commentSave(@ModelAttribute("CommentDTO") CommentDto comment) {

        assignmentService.createComment(comment);       // TODO : 예외처리 추가
        return "redirect:/admin/assignmentSubmit/" + comment.getSubmitId();
    }

    @PostMapping("/commentDelete")
    public String commentRemove(@RequestParam("id") int commentId, int submitId, RedirectAttributes redirectAttributes) {

        // TODO: result 값을 보내 화면에 알림창을 띄우도록 추가
        String message;
        if (assignmentService.deleteComment(commentId)) {
            message = "코멘트를 삭제하셨습니다!";
            redirectAttributes.addFlashAttribute("message", message);
        } else {
            message = "코멘트 삭제에 실패하셨습니다!";
            redirectAttributes.addFlashAttribute("message", message);
        }
        return "redirect:/admin/assignmentSubmit/" + submitId;
    }

}
