package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class InitializationUserDB {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public InitializationUserDB(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void createUsersWithRoles() {

        Role role1 = new Role("ADMIN");
        Role role2 = new Role( "USER");

        roleService.saveRole(role1);
        roleService.saveRole(role2);

        User user1 = new User("Попов", "Андрей", "andrey@email.com", new BCryptPasswordEncoder(8).encode("1234"));
        User user2 = new User("Петров", "Пётр", "petr@email.com", new BCryptPasswordEncoder(8).encode("1234"));
        User user3 = new User("Роман", "Романов", "romanov@email.com", new BCryptPasswordEncoder(8).encode("1234"));

        user1.setRoles(role1);
        user1.setRoles(role2);
        user2.setRoles(role1);
        user3.setRoles(role2);

        userService.saveUser(user1);
        userService.saveUser(user2);
        userService.saveUser(user3);
    }
}
