package com.synergies.synergy.controller;


import com.synergies.synergy.domain.dto.NotificationDto;
import com.synergies.synergy.domain.dto.TodoDto;
import com.synergies.synergy.service.NotificationService;
import com.synergies.synergy.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class TodoController {
    @Autowired
    private TodoService todoService;
    @Autowired
    private NotificationService notificationService;
    @GetMapping("/studentMain")
    public String getAll(Model model) throws ParseException {
        List<TodoDto> todoList = todoService.getAll();
        List<NotificationDto> notiList = notificationService.getAll();

        if (todoList.isEmpty()) {
            model.addAttribute("todoList", null);
            return "studentMain";
        }

        String[] date;
        Calendar getToday = Calendar.getInstance();
        getToday.setTime(new Date());

        for (TodoDto vo : todoList) {
            date = vo.getEndDate().split(" ");
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse(date[0]);
            Calendar cmpDate = Calendar.getInstance();
            cmpDate.setTime(end); //특정 일자

            long diffSec = (cmpDate.getTimeInMillis() - getToday.getTimeInMillis()) / 1000;
            long diffDays = diffSec / (24 * 60 * 60) + 1; //일자수 차이
            if (diffDays < 0) {
                vo.setEndDate("기간 만료 " + String.valueOf(diffDays));
            } else if (diffDays == 0) {
                vo.setEndDate("D-day");
            } else {
                vo.setEndDate("D-day: " + String.valueOf(diffDays));
            }
        }
        model.addAttribute("todoList", todoList);
        model.addAttribute("notiList", notiList);

        return "studentMain";
    }

    @GetMapping("/adminMain")
    public String getAlls(Model model) {
        List<TodoDto> todoList = todoService.getAll();
        if (todoList.isEmpty()) {
            model.addAttribute("todoList", null);
            return "adminMain";
        }
        model.addAttribute("todoList", todoList);
        return "adminMain";
    }


    @PostMapping("/todo/insert")
    public String todoInsert(@ModelAttribute TodoDto todo) {
        if (todo.getContent().isBlank() || todo.getEndDate().isBlank()) {
            return "redirect:/studentMain";
        }
        todoService.insert(todo);
        return "redirect:/studentMain";
    }


    @PostMapping("/todo/update/{id}")
    public String todoUpdate(@PathVariable int id, @ModelAttribute TodoDto todo) {
        if (todo.getContent().isBlank() || todo.getEndDate().isBlank()) {
            return "redirect:/studentMain";
        }
        todo.setId(id);
        todoService.update(todo);
        return "redirect:/studentMain";
    }

    @GetMapping("/todo/delete/{id}")
    public String todoDelete(@PathVariable int id) {
        todoService.delete(id);
        return "redirect:/studentMain";
    }

}
