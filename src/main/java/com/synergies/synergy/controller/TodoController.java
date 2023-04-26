package com.synergies.synergy.controller;

import com.synergies.synergy.domain.dto.TodoDto;
import com.synergies.synergy.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/todoPage")
    public String getAll(Model model) {
        List<TodoDto> todoList = todoService.getAll();
        if (todoList.isEmpty()){
            model.addAttribute("todoList", null);
            return "todoPage";
        }
        model.addAttribute("todoList", todoList);
        return "todoPage";
    }
    @GetMapping("/todoPagePro")
    public String getAlls(Model model) {
        List<TodoDto> todoList = todoService.getAll();
        if (todoList.isEmpty()){
            model.addAttribute("todoList", null);
            return "todoPagePro";
        }
        model.addAttribute("todoList", todoList);
        return "todoPagePro";
    }

    @GetMapping("/todo/updateForm/{id}")
    public String todoUpdateForm(@PathVariable int id, Model model){
        model.addAttribute("todo", new TodoDto(id));
        return "todoUpdateForm";
    }
    @PostMapping("/todo/insert")
    public String todoInsert(@ModelAttribute TodoDto todo) {
        if(todo.getContent().isBlank() || todo.getEndDate().isBlank()){
            return "redirect:/todoPage";
        }
        todo.setRefUserId("test_ref_user_id"); //세션에 있는 유저 아이디 셋팅
        todoService.insert(todo);
        return "redirect:/todoPage";
    }
    @PostMapping("/todo/update/{id}")
    public String todoUpdate(@PathVariable int id, @ModelAttribute TodoDto todo){
        System.out.println("test");
        if(todo.getContent().isBlank() || todo.getEndDate().isBlank()){
            return "redirect:/todoPage";
        }
        todo.setId(id);
        todo.setRefUserId("test_ref_user_id");  //세선에 있는 유저 아이디 셋팅
        todoService.update(todo);
        return "redirect:/todoPage";
    }

    @GetMapping("/todo/delete/{id}")
    public String todoDelete(@PathVariable int id) {
        todoService.delete(id);
        return "redirect:/todoPage";
    }
}
