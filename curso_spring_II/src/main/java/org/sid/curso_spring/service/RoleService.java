package org.sid.curso_spring.service;

import org.sid.curso_spring.model.Role;

public interface RoleService {
    Role findByName(String name);
    Role save(Role role);
}
