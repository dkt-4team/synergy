package com.synergies.synergy.controller;

import com.synergies.synergy.auth.LoginAuth;
import com.synergies.synergy.domain.dto.AssignmentDto;
import com.synergies.synergy.domain.dto.NotificationDto;
import com.synergies.synergy.domain.vo.AssignmentVo;
import com.synergies.synergy.service.AssignmentService;
import com.synergies.synergy.service.NotificationService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private LoginAuth loginAuth;


    @GetMapping("/adminMain")
    public String notificationList(Model model, HttpSession session) {

        //과제, 공지사항 기능 테스트 하기 위해 주석처리
        /*
        관리자가 아닐 경우 접근 하지 못하도록 경로 우회
        if (!loginAuth.checkAuthority(session)) {
            return "redirect:/";
        }
        */

        List<NotificationDto> notiList = notificationService.notificationList();
        List<AssignmentVo> assigmentList = assignmentService.getTodayAssignment();
        model.addAttribute("AssignmentDTO", new AssignmentDto());

        // 공지 데이터 불러오기
        if (notiList.isEmpty()) {
            model.addAttribute("notificationList", null);
            model.addAttribute("assignmentList", assigmentList);
            return "pages/admin/adminMain";
        }

        model.addAttribute("notiList", notiList);

        // 오늘 등록된 과제 데이터 불러오기
        if (assigmentList.isEmpty()) {
            model.addAttribute("assignmentList", null);
        } else {
            model.addAttribute("assignmentList", assigmentList);
        }

        return "pages/admin/adminMain";
    }


    @PostMapping("/notificationSave")
    public String notificationAdd(NotificationDto notification) {

        if (notification.getContent().isBlank() || notification.getTitle().isBlank()
            || notification.getLabelOption().isBlank()) {

            return "redirect:/adminMain";
        }
        notificationService.notificationAdd(notification);
        return "redirect:/adminMain";
    }


    @PostMapping("/notificationUpdate/{id}")
    public String notificationModify(@PathVariable int id,
        @ModelAttribute NotificationDto notification) {
        if (notification == null || notification.getContent().isBlank() || notification.getTitle()
            .isBlank() || notification.getLabelOption().isBlank()) {
            return "redirect:/adminMain";
        }
        notification.setId(id);
        notificationService.notificationModify(notification);
        return "redirect:/adminMain";
    }


    @GetMapping("/notificationDelete/{id}")
    public String notificationRemove(@PathVariable int id) {
        notificationService.notificationRemove(id);
        return "redirect:/adminMain";
    }
}
