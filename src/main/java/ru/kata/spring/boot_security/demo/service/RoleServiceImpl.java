package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    private final RoleDao roleRepository;

    @Autowired
    public RoleServiceImpl(RoleDao roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.getAllRoles();
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.getRoleById(id);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void update(Long id, Role updatedRole) {
        Role role = getRoleById(id);
        role.setName(updatedRole.getName());
        role.setUsers(updatedRole.getUsers());
    }

    @Override
    public void delete(Long id) {
        roleRepository.delete(id);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleRepository.getRoleByName(roleName);
    }

    @Override
    public List<Role> setRoleByName(String name, String[] rolesName) {
        return roleRepository.setRoleByName(name, rolesName);
    }

    @Override
    public List<Role> setRoles(String[] rolesNames) {
        return roleRepository.setRoles(rolesNames);
    }
}
