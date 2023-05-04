package com.synergies.synergy.controller;

import com.synergies.synergy.domain.dto.AssignmentDto;
import com.synergies.synergy.domain.dto.NotificationDto;
import com.synergies.synergy.domain.dto.TodoDto;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private AssignmentService assignmentService;


//    public List<NotificationDto> formattedRegDate(List<NotificationDto> notiList) {
//        List<NotificationDto> notidto = notificationService.notificationList();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        String formattedDate;
//
//        for (for int i = 0; i < notiList.size();i ) {
//            formattedDate = formatter.format(notification.getRegDate());
//            notification.setRegDate(formattedDate);
//        }
//        return notiList;
//    }


    @GetMapping("/adminMain")
    public String notificationList(Model model) {
        List<NotificationDto> notiList = notificationService.notificationList();
        List<AssignmentVo> assigmentList = assignmentService.getTodayAssignment();
        model.addAttribute("AssignmentDTO", new AssignmentDto());

        // 공지 데이터 불러오기
        if (notiList.isEmpty()){
            model.addAttribute("notificationList", null);
            model.addAttribute("assignmentList", assigmentList);
            return "adminMain";
        }
        //notiList = formattedRegDate(notiList);

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


    @PostMapping("/notificationSave")
    public String notificationAdd(NotificationDto notification) {


        if(notification.getContent().isBlank() || notification.getTitle().isBlank() || notification.getLabelOption().isBlank()){

            return "redirect:/adminMain";
        }
        notificationService.notificationAdd(notification);
        return "redirect:/adminMain";
    }


    @PostMapping("/notificationUpdate/{id}")
    public String notificationModify(@PathVariable int id, @ModelAttribute NotificationDto notification){
        if(notification == null  || notification.getContent().isBlank() || notification.getTitle().isBlank()|| notification.getLabelOption().isBlank()){
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
