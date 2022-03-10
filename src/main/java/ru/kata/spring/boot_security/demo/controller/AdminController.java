package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


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
        return "admin/getAll";
    }

    @GetMapping("/{id}")
    public String getUsersByIdController(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getByIdUser(id));
        return "admin/getId";
    }

    @PostMapping()
    public String save(@ModelAttribute("user") User user,
                       @RequestParam("roles") String[] roles) {
        user.setRoles(roleService.setRoles(roles));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/save";
    }

    @GetMapping("/{id}/update")
    public String update(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getByIdUser(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/update";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("roles") String[] roles, @PathVariable("id") Long id) {
        user.setRoles(roleService.setRoles(roles));
        userService.userUpdate(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
