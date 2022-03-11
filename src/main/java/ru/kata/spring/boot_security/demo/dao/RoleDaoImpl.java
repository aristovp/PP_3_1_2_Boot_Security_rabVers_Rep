//package ru.kata.spring.boot_security.demo.dao;
//
//import org.springframework.stereotype.Repository;
//import ru.kata.spring.boot_security.demo.model.Role;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Repository
//public class RoleDaoImpl implements RoleDao {
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Override
//    public Set<Role> getAllRoles() {
//        return em.createQuery("from Role", Role.class).getResultStream().collect(Collectors.toSet());
//    }
//
//    @Override
//    public Role getRoleById(Long id) {
//        return (Role) em.createQuery("from Role r where r.id=:id")
//                .setParameter("id", id).getSingleResult();
//    }
//
//    @Override
//    public void save(Role role) {
//        em.persist(role);
//    }
//
//    @Override
//    public void update(Role updatedRole) {
//        em.merge(updatedRole);
//    }
//
//    @Override
//    public void delete(Long id) {
//        em.createQuery("delete from Role name where name.id = ?1")
//                .setParameter(1, id)
//                .executeUpdate();
//    }
//
//    @Override
//    public Role getRoleByName(String roleName) {
//        return (Role) em.createQuery("from Role r where r.name=:name")
//                .setParameter("name", roleName)
//                .getSingleResult();
//    }
//
//    @Override
//    public Set<Role> setRoleByName(String name, String[] rolesName) {
//        Set<Role> roleSet = new HashSet<>();
//        if (rolesName != null) {
//            for (String roleName : rolesName) {
//                roleSet.add(getRoleByName(roleName));
//            }
//        }
//        return roleSet;
//    }
//
//    @Override
//    public Set<Role> setRoles(String[] rolesNames) {
//        Set<Role> roleSet = new HashSet<>();
//        for (String role : rolesNames) {
//            roleSet.add(getRoleByName(role));
//        }
//        return roleSet;
//    }
//}
