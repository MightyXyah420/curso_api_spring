package org.sid.curso_spring.service;

import java.util.List;

import org.sid.curso_spring.dto.UserDto;
import org.sid.curso_spring.model.User;


 //Interfaz para definir ñas caracteristicas de un servicio
 //más no la implementación

public interface UserService {
    User save(UserDto user);
    List<User> findAll();
    User findOne(String username);
}
