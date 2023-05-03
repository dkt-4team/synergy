package com.synergies.synergy.controller;

import com.synergies.synergy.domain.dto.AssignmentDto;
import com.synergies.synergy.domain.dto.NotificationDto;
import com.synergies.synergy.domain.vo.AssignmentVo;
import com.synergies.synergy.service.AssignmentService;
import com.synergies.synergy.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private AssignmentService assignmentService;

    @GetMapping("/adminMain")
    public String getAll(Model model) {
        List<NotificationDto> notiList = notificationService.getAll();
        List<AssignmentVo> assigmentList = assignmentService.getTodayAssignment();
        model.addAttribute("AssignmentDTO", new AssignmentDto());

        // 공지 데이터 불러오기
        if (notiList.isEmpty()){
            model.addAttribute("notificationList", null);
            model.addAttribute("assignmentList", assigmentList);
            return "adminMain";
        }
        for (NotificationDto notification : notiList) {
            notification.setRegDate(notification.formattedRegDate());
        }
        model.addAttribute("notiList", notiList);
        
        // 오늘 등록된 과제 데이터 불러오기
        if (assigmentList.isEmpty()) {
            model.addAttribute("assignmentList", null);
        }
        else {
            model.addAttribute("assignmentList", assigmentList);
        }
        
        return "adminMain";
    }


    @PostMapping("/notification/insert")
    public String notificationInsert(NotificationDto notification) {

        notification.setRegDate("2023-05-02");


        if(notification.getContent().isBlank() || notification.getTitle().isBlank() || notification.getLabelOption().isBlank()){
            return "redirect:/adminMain";
        }

        notificationService.insert(notification);
        return "redirect:/adminMain";
    }


    @PostMapping("/notification/update/{id}")
    public String notificationUpdate(@PathVariable int id, @ModelAttribute NotificationDto notification){
        if(notification == null  || notification.getContent().isBlank() || notification.getTitle().isBlank()|| notification.getRegDate().isBlank() || notification.getLabelOption().isBlank()){
            return "redirect:/adminMain";
        }
        notification.setId(id);
        notificationService.update(notification);
        return "redirect:/adminMain";
    }




    @GetMapping("/notification/delete/{id}")
    public String notificationDelete(@PathVariable int id) {
        notificationService.delete(id);
        return "redirect:/adminMain";
    }
}
