package com.synergies.synergy.controller;

import com.synergies.synergy.domain.dto.NotificationDto;
import com.synergies.synergy.domain.dto.TodoDeleteRequestDTO;
import com.synergies.synergy.domain.dto.TodoDto;
import com.synergies.synergy.domain.vo.LoginUserInfoVO;
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
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("loginUserInfo")
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
    public String getAll(Model model) throws ParseException {

        LoginUserInfoVO loginUserInfo = (LoginUserInfoVO)model.getAttribute("loginUserInfo");
        if(loginUserInfo == null || loginUserInfo.getUserId() == null){
            return "redirect:/";
        }

        List<TodoDto> todoList = changeDateFormat(todoService.getAll(loginUserInfo.getUserId()));
        List<NotificationDto> notiList = notificationService.getAll();

        model.addAttribute("todo", new TodoDto());
        model.addAttribute("notiList", notiList);

        if (notiList.isEmpty()) {
            model.addAttribute("notiList", null);
        }

        if (todoList.isEmpty()) {
            model.addAttribute("todoList", null);
            return "studentMain";
        }
        model.addAttribute("todoList", todoList);

        return "studentMain";
    }

    @PostMapping("/todo/insert")
    public String todoInsert(@ModelAttribute("todo") TodoDto todo, Model model) {

        LoginUserInfoVO loginUserInfo = ((LoginUserInfoVO)model.getAttribute("loginUserInfo"));
        if(loginUserInfo == null || loginUserInfo.getUserId() == null){
            return "redirect:/home";
        }

        if (todo.getContent().isBlank() || todo.getEndDate().isBlank()) {
            return "redirect:/home";
        }
        Date curDate = new Date();
        todo.setRegDate(curDate);
        todo.setRefUserId(loginUserInfo.getUserId());
        todoService.insert(todo);
        return "redirect:/home";
    }


    @PostMapping("/todo/update/{id}")
    public String todoUpdate(@PathVariable int id, @ModelAttribute("todo") TodoDto todo, Model model) {

        LoginUserInfoVO loginUserInfo = ((LoginUserInfoVO)model.getAttribute("loginUserInfo"));
        if(loginUserInfo == null || loginUserInfo.getUserId() == null){
            return "redirect:/home";
        }

        if (todo.getContent().isBlank() || todo.getEndDate().isBlank()) {
            return "redirect:/home";
        }
        todo.setId(id);
        todo.setRefUserId(loginUserInfo.getUserId());
        todoService.update(todo);
        return "redirect:/home";
    }

    @GetMapping("/todo/delete/{id}")
    public String todoDelete(@PathVariable int id, Model model) {

        LoginUserInfoVO loginUserInfo = ((LoginUserInfoVO)model.getAttribute("loginUserInfo"));
        if(loginUserInfo == null || loginUserInfo.getUserId() == null){
            return "redirect:/home";
        }

        todoService.delete(new TodoDeleteRequestDTO(id, loginUserInfo.getUserId()));
        return "redirect:/home";
    }
}
