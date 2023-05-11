package com.synergies.synergy.controller;

import com.synergies.synergy.domain.dto.NotificationDto;
import com.synergies.synergy.domain.vo.LoginUserInfoVo;
import com.synergies.synergy.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/notificationSave")
    public String notificationAdd(NotificationDto notification, HttpSession session) {

        if (notification.getContent().isBlank() || notification.getTitle().isBlank()
                || notification.getLabelOption().isBlank()) {
            //alert 보내기
            return "redirect:/admin/home";
        }
        notification.setRefUserId(((LoginUserInfoVo) session.getAttribute("loginUserInfo")).getId());
        notificationService.notificationAdd(notification);
        return "redirect:/admin/home";
    }


    @PostMapping("/notificationUpdate/{id}")
    public String notificationModify(@PathVariable int id,
        @ModelAttribute NotificationDto notification, RedirectAttributes redirectAttributes, HttpSession session) {
        if (notification == null || notification.getContent().isBlank() || notification.getTitle()
                .isBlank() || notification.getLabelOption().isBlank()) {
            return "redirect:/admin/home";
        }

        notification.setId(id);
        notification.setRefUserId(((LoginUserInfoVo)session.getAttribute("loginUserInfo")).getId());
        notificationService.notificationModify(notification);

        redirectAttributes.addFlashAttribute("message", "공지를 수정하셨습니다!");
        return "redirect:/admin/home";
    }


    @GetMapping("/notificationDelete/{id}")
    public String notificationRemove(@PathVariable int id, RedirectAttributes redirectAttributes) {
        notificationService.notificationRemove(id);

        redirectAttributes.addFlashAttribute("message", "공지를 삭제하셨습니다!");
        return "redirect:/admin/home";
    }
}
