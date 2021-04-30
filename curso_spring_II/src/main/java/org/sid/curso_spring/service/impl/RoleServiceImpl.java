package org.sid.curso_spring.service.impl;

import org.sid.curso_spring.dao.RoleDao;
import org.sid.curso_spring.model.Role;
import org.sid.curso_spring.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//Sugerencia para el nombre a la hora de instanciar
//Opcional, pero se recomienda seguir el estandar
@Service(value = "roleService")
//Implementación de role service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleDao roleDao;
    @Override
    public Role findByName(String name) {
        Role role = roleDao.findRoleByName(name);
        return role;
    }
    @Override
    public Role save(Role role) {
        return roleDao.save(role);
    }
}
