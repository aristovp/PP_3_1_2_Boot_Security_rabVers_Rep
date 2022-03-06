package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Role> getAllRoles() {
        return (List<Role>) em.createQuery("from Role").getResultStream().collect(Collectors.toSet());
    }

    @Override
    public Role getRoleById(Long id) {
        return em.find(Role.class, id);
    }

    @Override
    public void save(Role role) {
        em.persist(role);
    }

    @Override
    public void update(Long id, Role updatedRole) {
        em.merge(updatedRole);
    }

    @Override
    public void delete(Long id) {
        em.createQuery("delete from Role role where role.id = ?1")
                .setParameter(1, id)
                .executeUpdate();
    }


}
