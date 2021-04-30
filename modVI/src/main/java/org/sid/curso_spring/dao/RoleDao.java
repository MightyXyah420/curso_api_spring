package org.sid.curso_spring.dao;

import org.sid.curso_spring.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository <Role, Long> {
    Role findRoleByName(String name);
    
}
