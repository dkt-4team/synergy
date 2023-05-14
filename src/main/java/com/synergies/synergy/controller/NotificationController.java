package com.synergies.synergy.controller;

import com.synergies.synergy.domain.dto.NotificationDto;
import com.synergies.synergy.domain.vo.LoginUserInfoVo;
import com.synergies.synergy.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/admin")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/notificationSave")
    public String notificationAdd(NotificationDto notification, HttpSession session) {

        if (notification.getContent().isBlank() || notification.getTitle().isBlank()
                || notification.getLabelOption().isBlank()) {
            log.info("빈 내용으로 인한 add 거부");
            //alert 보내기
            return "redirect:/admin/home";
        }
        notification.setRefUserId(((LoginUserInfoVo) session.getAttribute("loginUserInfo")).getId());
        notificationService.readNotification(notification);
        log.info("공지사항 add 성공 + 공지사항 데이터 : " + notification);
        return "redirect:/admin/home";
    }


    @PostMapping("/notificationUpdate/{id}")
    public String notificationModify(@PathVariable int id,
                                     @ModelAttribute NotificationDto notification, RedirectAttributes redirectAttributes, HttpSession session) {
        if (notification == null || notification.getContent().isBlank() || notification.getTitle()
                .isBlank() || notification.getLabelOption().isBlank()) {
            log.info("빈 내용으로 인한 update 거부");
            return "redirect:/admin/home";
        }

        notification.setId(id);
        notification.setRefUserId(((LoginUserInfoVo) session.getAttribute("loginUserInfo")).getId());
        notificationService.updateNotification(notification);
        log.info("공지사항 update 성공 + 공지사항 데이터 : " + notification);

        redirectAttributes.addFlashAttribute("message", "공지를 수정하셨습니다!");
        return "redirect:/admin/home";
    }


    @GetMapping("/notificationDelete/{id}")
    public String notificationRemove(@PathVariable int id, RedirectAttributes redirectAttributes) {
        notificationService.deleteNotification(id);
        log.info("공지사항 delete 성공 + 공지사항 id : " + id);
        redirectAttributes.addFlashAttribute("message", "공지를 삭제하셨습니다!");
        return "redirect:/admin/home";
    }
}
