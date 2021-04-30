package org.sid.curso_spring.dao;

import org.sid.curso_spring.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository <User, Long> {
    User findByUsername(String username);
}
