package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;


public interface UserService {
    User getByIdUser(Long id);

    List<User> getAllUsers();

    void saveUser(User user);

    void deleteUser(Long id);

    void userUpdate(Long id, User userUpdate);

    UserDetails loadUserByUsername(String username);

}
