package org.sid.curso_spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.sid.curso_spring.dao.NotaDao;
import org.sid.curso_spring.model.Nota;
import org.sid.curso_spring.model.User;
import org.sid.curso_spring.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "notaService")
public class NotaServiceImpl implements NotaService {
    @Autowired
    private NotaDao notaDao;
    @Autowired
    private UserServiceImpl userService;
    
    public List <Nota> test(User user){
        return notaDao.findByUser(user);
    }
    @Override
    public Optional<Nota> readNota(long id){
        return notaDao.findById(id);
    }
    public Iterable<Nota> findAll(){
        return notaDao.findAll();
    }
    @Override
    public Nota save(Nota nota) {
        // TODO Auto-generated method stub

        return notaDao.save(nota);
    }
    @Override
    public void delete(Nota nota) {
        notaDao.delete(nota);
        // TODO Auto-generated method stub
        
    }

}
