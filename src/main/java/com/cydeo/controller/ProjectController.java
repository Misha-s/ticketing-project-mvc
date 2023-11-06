package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    public ProjectController(ProjectService projectService,UserService userService) {

        this.projectService = projectService;
        this.userService = userService;
    }

    ProjectService projectService;
    UserService userService;

    @GetMapping("/create")
    public String createProject(Model model){

    model.addAttribute("project", new ProjectDTO());
    model.addAttribute("projects",projectService.findAll());
    model.addAttribute("managers", userService.findManagers());
        return ("/project/create");
    }

    @PostMapping("/create")
    public String addProject(@ModelAttribute("project") ProjectDTO project){

       projectService.save(project);

       return "redirect:/project/create";
    }

    @GetMapping("/delete/{projectcode}")
    public String deleteProject(@PathVariable("projectcode") String projectCode){

        projectService.deleteById(projectCode);

        return "redirect:/project/create";
    }


    @GetMapping("/complete/{projectcode}")
    public String completeProject(@PathVariable("projectcode") String projectCode){
        projectService.complete(projectService.findById(projectCode));
        return "redirect:/project/create";
        }

    @GetMapping("/update/{projectcode}")
    public String editProject(@PathVariable("projectcode") String projectCode,Model model){
        model.addAttribute("project", projectService.findById(projectCode));
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("managers", userService.findManagers());
        return "/project/update";
    }

    @PostMapping("/update")
    public String updateProject(ProjectDTO projectDTO){
       projectService.update(projectDTO);
        return "redirect:/project/create";
    }


    @GetMapping("/manager/project-status")
    public String getProjectByManager(Model model){

        UserDTO manager = userService.findById("john@cydeo.com");

        List<ProjectDTO> projects = projectService.getCountedListOfProjectDTO(manager);

        model.addAttribute("projects",projects);

        return "/manager/project-status";
    }

    @GetMapping("/manager/complete/{projectCode}")
    public String managerCompleteProject(@PathVariable("projectCode") String projectCode) {
        projectService.complete(projectService.findById(projectCode));
        return "redirect:/project/manager/project-status";
    }
}
