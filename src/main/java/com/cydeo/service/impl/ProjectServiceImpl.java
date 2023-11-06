package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractMapService<String, ProjectDTO> implements ProjectService {

    private final TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }


    @Override
    public ProjectDTO save(ProjectDTO object) {

        if(object.getProjectStatus() == null){
            object.setProjectStatus(Status.OPEN);
        }
        return super.save(object.getProjectCode(),object);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(ProjectDTO object) {
        if(object.getProjectStatus()==null)
            object.setProjectStatus(findById(object.getProjectCode()).getProjectStatus());
        super.update(object.getProjectCode(),object);
    }

    @Override
    public void deleteById(String id) {
        super.deleteById(id);
    }

    @Override
    public ProjectDTO findById(String id) {
        return super.findById(id);
    }
    @Override
    public void complete(ProjectDTO project){
        project.setProjectStatus(Status.COMPLETE);
        super.save(project.getProjectCode(), project);
    }

    @Override
    public List<ProjectDTO> findAllNonCompletedProjects() {
       return findAll().stream().filter(project -> project.getProjectStatus().equals(Status.COMPLETE)).collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {
        List<ProjectDTO> projectDTOList = findAll().stream()
                .filter(projectDTO ->projectDTO.getAssignedManager().equals(manager))
                .map(projectDTO -> {

                    List<TaskDTO> taskList = taskService.findTasksByManager(manager);


                    int completedTask = (int) taskList.stream().filter(t -> t.getProject().equals(projectDTO)&& t.getTaskStatus() == Status.COMPLETE).count();
                    int unfinishedTasks = (int) taskList.stream().filter(t -> t.getProject().equals(projectDTO)&& t.getTaskStatus() != Status.COMPLETE).count();

                    projectDTO.setCompletedTaskCount(completedTask);
                    projectDTO.setUnfinishedTaskCount(unfinishedTasks);
                    return projectDTO;
                }).collect(Collectors.toList());

                return projectDTOList;
    }
}
