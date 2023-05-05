package com.synergies.synergy.controller;

import com.synergies.synergy.domain.dto.NotificationDto;
import com.synergies.synergy.domain.dto.TodoDeleteRequestDto;
import com.synergies.synergy.domain.dto.TodoDto;
import com.synergies.synergy.domain.vo.LoginUserInfoVo;
import com.synergies.synergy.service.NotificationService;
import com.synergies.synergy.service.TodoService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
public class TodoController {

    @Autowired
    private TodoService todoService;
    @Autowired
    private NotificationService notificationService;

    private List<TodoDto> changeDateFormat(List<TodoDto> list) throws ParseException {
        String[] date;
        Calendar getToday = Calendar.getInstance();
        getToday.setTime(new Date());
        long diffSec;
        long diffDays;
        Calendar cmpDate;

        for (TodoDto vo : list) {
            date = vo.getEndDate().split(" ");
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse(date[0]);
            cmpDate = Calendar.getInstance();
            cmpDate.setTime(end); //특정 일자

            diffSec = (cmpDate.getTimeInMillis() - getToday.getTimeInMillis()) / 1000;
            diffDays = diffSec / (24 * 60 * 60) + 1; //일자수 차이

            if (diffDays < 0) {
                vo.setEndDate("기간 만료 " + diffDays + "|" + vo.getEndDate());
            } else if (diffDays == 0) {
                vo.setEndDate("D-day" + "|" + vo.getEndDate());
            } else {
                vo.setEndDate("D-day: " + diffDays + "|" + vo.getEndDate());
            }
        }

        return list;
    }

    @GetMapping("/home")
    public String getAll(Model model, HttpSession session) throws ParseException {
        List<TodoDto> todoList = changeDateFormat(todoService.selectAllTodo(
            ((LoginUserInfoVo) session.getAttribute("loginUserInfo")).getUserId()));
        List<NotificationDto> notiList = notificationService.notificationList();

        model.addAttribute("todo", new TodoDto());
        model.addAttribute("notiList", notiList);

        if (notiList.isEmpty()) {
            model.addAttribute("notiList", null);
        }

        if (todoList.isEmpty()) {
            model.addAttribute("todoList", null);
            return "pages/student/studentMain";
        }

        model.addAttribute("todoList", todoList);

        return "pages/student/studentMain";
    }

    @PostMapping("/todo/insert")
    public String todoInsert(@ModelAttribute("todo") TodoDto todo, HttpSession session) {
        if (todo.getContent().isBlank() || todo.getEndDate().isBlank()) {
            return "redirect:/home";
        }
        Date curDate = new Date();
        todo.setRegDate(curDate);
        todo.setRefUserId(((LoginUserInfoVo) session.getAttribute("loginUserInfo")).getUserId());
        todoService.insertTodo(todo);
        return "redirect:/home";
    }


    @PostMapping("/todo/update/{id}")
    public String todoUpdate(@PathVariable int id, @ModelAttribute("todo") TodoDto todo,
        HttpSession session) {
        if (todo.getContent().isBlank() || todo.getEndDate().isBlank()) {
            return "redirect:/home";
        }
        todo.setId(id);
        todo.setRefUserId(((LoginUserInfoVo) session.getAttribute("loginUserInfo")).getUserId());
        todoService.updateTodo(todo);
        return "redirect:/home";
    }

    @PostMapping("/todo/delete")
    public String todoDelete(int id, HttpSession session) {
        todoService.deleteTodo(new TodoDeleteRequestDto(id,
            ((LoginUserInfoVo) session.getAttribute("loginUserInfo")).getUserId()));
        return "redirect:/home";
    }
}
