package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;


public interface UserService extends UserDetailsService{

    List<User> getAllUsers();
    void saveUser(User user);
    User getUserById(Long id);
    void deleteUserById(Long id);
    UserDetails loadUserByUsername(String email);


//    void userUpdate(Long id, User userUpdate);
//    UserDetails findUserByEmail(String email);
//    void setRoleByUser(User user, String[] roles);
}
