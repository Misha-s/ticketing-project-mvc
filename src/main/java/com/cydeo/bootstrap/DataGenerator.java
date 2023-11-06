package com.cydeo.bootstrap;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Gender;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.RoleService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import com.cydeo.service.impl.RoleServiceImpl;
import com.cydeo.service.impl.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
@Component
public class DataGenerator implements CommandLineRunner {

    RoleService roleService;
    UserService userService;
    ProjectService projectService;
    TaskService taskService;

    public DataGenerator(UserService userService,RoleService roleService,ProjectService projectService, TaskService taskService) {
        this.userService = userService;
        this.roleService = roleService;
        this.projectService= projectService;
        this.taskService = taskService;
    }


    @Override
    public void run(String... args) throws Exception{

        RoleDTO adminRole = new RoleDTO(1L,"Admin");
        RoleDTO managerRole = new RoleDTO(2L,"Manager");
        RoleDTO employeeRole = new RoleDTO(3L,"Employee");

        roleService.save(adminRole);
        roleService.save(managerRole);
        roleService.save(employeeRole);

        UserDTO user1 = new UserDTO("John","Kesy","john@cydeo.com","Abc123qw3",true,"3244109640",managerRole, Gender.MALE);
        UserDTO user2 = new UserDTO("Jessica","Keesy","Jessicakessy@tomato.com","Abc1239083",true,"3144109631",adminRole, Gender.FEMALE);
        UserDTO user3 = new UserDTO("Maria","Ketler","MariaKetler@tomato.com","qwert1Mama083",true,"3474590234",managerRole, Gender.FEMALE);
        UserDTO user4 = new UserDTO("Mike","Smith","MikeSmith@tomato.com","Abcqykfw3",true,"3474590290",managerRole, Gender.MALE);
        UserDTO user5 = new UserDTO("Denisee","Waterson","Denisee@tomato.com","Abcqykfw3",true,"347002290",employeeRole, Gender.FEMALE);
        UserDTO user6 = new UserDTO("Isak","Emre","Isak@tomato.com","jkdfsjksfsd",true,"3450590290",employeeRole, Gender.MALE);
        UserDTO user7 = new UserDTO("Ikbol","Imkenson","Ikbol@tomato.com","sdfs34sdf2",true,"3471000290",employeeRole, Gender.MALE);
        UserDTO user8 = new UserDTO("Trevor","Johnson","Trevor@tomato.com","sdf866743",true,"7864590290",employeeRole, Gender.MALE);

        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);
        userService.save(user5);
        userService.save(user6);
        userService.save(user7);
        userService.save(user8);


        ProjectDTO project1 = new ProjectDTO("Spring MVC", "PRT001", user1, LocalDate.now(),LocalDate.now().minusDays(25),"Creating Contorllers", Status.OPEN);
        ProjectDTO project2 = new ProjectDTO("Spring ORM", "PRT002", user3, LocalDate.now(),LocalDate.now().minusDays(15),"Creating Contorllers", Status.IN_PROGRESS);
        ProjectDTO project3 = new ProjectDTO("Spring Container", "PRT003", user4, LocalDate.now(),LocalDate.now().minusDays(12),"Creating Contorllers", Status.IN_PROGRESS);

        projectService.save(project1);
        projectService.save(project2);
        projectService.save(project3);

        TaskDTO task1 = new TaskDTO(project1, user8, "Controller", "Request Mapping", Status.IN_PROGRESS, LocalDate.now().minusDays(4));
        TaskDTO task2 = new TaskDTO(project3, user3, "Configuration", "Database Connection", Status.COMPLETE, LocalDate.now().minusDays(12));
        TaskDTO task3 = new TaskDTO(project3, user6, "Mapping", "One-To-Many", Status.COMPLETE, LocalDate.now().minusDays(8));
        TaskDTO task4 = new TaskDTO(project2, user7, "Dependency Injection", "Autowired", Status.IN_PROGRESS, LocalDate.now().minusDays(20));

        taskService.save(task1);
        taskService.save(task2);
        taskService.save(task3);
        taskService.save(task4);


    }

}
