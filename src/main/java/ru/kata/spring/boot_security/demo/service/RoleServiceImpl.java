package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> getAllRoles() {
        return roleRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Transactional
    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public Role getRole(String role) {
        return roleRepository.getRole(role);
    }



//    public Role getRoleById(Long id) {
//        return roleRepository.getRoleById(id);
//    }
//
////    @Override
////    public void update(Long id, Role updatedRole) {
////        roleRepository.(updatedRole);
////    }
//

//
//    @Override
//    public Role getRoleByName(String roleName) {
//        return roleRepository.getRoleByName(roleName);
//    }

//    @Override
//    public Set<Role> setRoleByName(String name, String[] rolesName) {
//        return roleRepository.setRoleByName(name, rolesName);
//    }

////    @Override
//    public Set<Role> setRoles(String[] rolesNames) {
//        return roleRepository.setRoles(rolesNames);
//    }
}
