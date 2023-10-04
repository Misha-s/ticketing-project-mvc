package com.cydeo.bootstrap;

import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Gender;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import com.cydeo.service.impl.RoleServiceImpl;
import com.cydeo.service.impl.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DataGenerator implements CommandLineRunner {

    RoleService roleService;
    UserService userService;

    public DataGenerator(UserService userService,RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @Override
    public void run(String... args) throws Exception{

        RoleDTO adminRole = new RoleDTO(1L,"Admin");
        RoleDTO managerRole = new RoleDTO(2L,"Manager");
        RoleDTO employeeRole = new RoleDTO(3L,"Employee");

        roleService.save(adminRole);
        roleService.save(managerRole);
        roleService.save(employeeRole);

        UserDTO user1 = new UserDTO("John","Kesy","Johnkessy@tomato.com","Abc123qw3",true,"3244109640",managerRole, Gender.MALE);
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


    }

}
