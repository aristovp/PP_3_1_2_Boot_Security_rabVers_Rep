package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String getAllUsersController(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/getAll";
    }

    @GetMapping("/{id}")
    public String getUsersByIdController(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/getId";
    }

    @PostMapping()
    public String save(@ModelAttribute("user") User user,
                       @RequestParam(value = "roleId", required = false)
                               Long[] roleId) {
        if (roleId != null) {
            user.getRoles().addAll(Arrays.stream(roleId)
                    .map(id -> roleService.getRoleById(id))
                    .collect(Collectors.toSet()));
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/save";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/update";
    }

    @PostMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "roleId", required = false)
                                     Long[] roleId) {
        if (roleId != null) {
            user.getRoles().addAll(Arrays.stream(roleId)
                    .map(id -> roleService.getRoleById(id))
                    .collect(Collectors.toSet()));
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }
}
