package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    List<Role> getAllRoles();
    Role getRoleById(Long id);
    void save(Role role);
    void update(Long id, Role updatedRole);
    void delete(Long id);

}
