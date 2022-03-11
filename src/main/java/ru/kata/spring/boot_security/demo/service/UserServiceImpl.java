package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import java.util.List;



@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }


    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.getUserByUserName(email);
    }
}


//
////    @Override
////    @Transactional
////    public void userUpdate(Long id, User updatedUser) {
////        userRepository.update(id, updatedUser);
////    }
//
//    @Override
//    @Transactional
//    public void userUpdate(Long id, User userUpdate) {
//        User userUpd = getByIdUser(id);
//        userUpd.setFirstName(userUpdate.getFirstName());
//        userUpd.setLastName(userUpdate.getLastName());
//        userUpd.setEmail(userUpdate.getEmail());
//    }
//
//
//    @Override
//    public UserDetails findUserByEmail(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email);
//
//        if (user == null) {
//            throw new UsernameNotFoundException(String.format("no such username %s", email));
//        }
//        return user;
//    }
//
//    public void setRoleByUser(User user, String[] roles) {
//        Role role = new Role();
//        for (String rol : roles) {
//            role.setName(rol);
//            user.setRoles(role);
//        }
//    }
////
////    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
////        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
////    }
//
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        return userRepository.findByEmail(username);
////    }
//}
