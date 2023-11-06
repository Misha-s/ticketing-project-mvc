package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/task")
public class TaskController {

    public TaskController(ProjectService projectService, UserService userService, TaskService taskService) {
        this.projectService = projectService;
        this.userService = userService;
        this.taskService = taskService;
    }

    private final ProjectService projectService;
    private final UserService userService;
    private final TaskService taskService;

    @GetMapping("/create")
    public String createTask(Model model){
        model.addAttribute("task",new TaskDTO());
        model.addAttribute("tasks",taskService.findAll());
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("employees",userService.findEmployees());

        return ("/task/create");
    }

    @PostMapping("/create")
    public String addTask(@ModelAttribute("task") TaskDTO task){

        taskService.save(task);
        return "redirect:/task/create";
    }

    @GetMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable("taskId") Long taskId){

        taskService.deleteById(taskId);
        return "redirect:/task/create";
    }

    @GetMapping("/update/{taskId}")
    public String editTask(@PathVariable("taskId") Long taskId, Model model) {

        model.addAttribute("task", taskService.findById(taskId));
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findEmployees());
        model.addAttribute("tasks", taskService.findAll());

        return "task/update";

    }

//    @PostMapping("/update/{id}")
//    public String updateTask(TaskDTO task) {
//        taskService.update(task);
//        return "redirect:/task/create";
//    }

    @PostMapping("/update/{id}")
    public String updateTask(@Valid @ModelAttribute("task") TaskDTO task, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("projects", projectService.findAll());
            model.addAttribute("employees", userService.findEmployees());
            model.addAttribute("tasks", taskService.findAll());

            return "/task/update";

        }

        taskService.update(task);
        return "redirect:/task/create";
    }

    @GetMapping("/employee/pending-tasks")
    public String employeePendingTasks(Model model) {
        model.addAttribute("tasks", taskService.findAllTasksByStatusIsNot(Status.COMPLETE));
        return "task/pending-tasks";
    }

    @GetMapping("/employee/edit/{id}")
    public String employeeEditTask(@PathVariable("id") Long id, Model model) {

        model.addAttribute("task", taskService.findById(id));
        model.addAttribute("tasks", taskService.findAllTasksByStatusIsNot(Status.COMPLETE));
        model.addAttribute("statuses", Status.values());

        return "task/status-update";

    }

    @PostMapping("/employee/update/{id}")
    public String employeeUpdateTask(@Valid @ModelAttribute("task") TaskDTO task, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("tasks", taskService.findAllTasksByStatusIsNot(Status.COMPLETE));
            model.addAttribute("statuses", Status.values());

            return "/task/status-update";

        }

        taskService.updateStatus(task);
        return "redirect:/task/employee/pending-tasks";

    }

    @GetMapping("/employee/archive")
    public String employeeArchivedTasks(Model model) {
        model.addAttribute("tasks", taskService.findAllTasksByStatus(Status.COMPLETE));
        return "task/archive";
    }

}
