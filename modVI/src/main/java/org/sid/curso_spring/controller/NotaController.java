package org.sid.curso_spring.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.sid.curso_spring.dto.NotaCreateDto;
import org.sid.curso_spring.dto.NotaDto;
import org.sid.curso_spring.model.Nota;
import org.sid.curso_spring.model.User;
import org.sid.curso_spring.service.impl.NotaServiceImpl;
import org.sid.curso_spring.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/notas")
public class NotaController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private NotaServiceImpl notaService;
    
    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public Iterable<Nota> getAll(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userService.findOne(auth.getName());
        return notaService.findAll();
    }
    @PostMapping("/updateNota")
    public Nota modNota (@RequestBody NotaDto body, HttpServletResponse response) throws IOException{
        Optional<Nota> existingNote = notaService.readNota(body.getId());
        if(existingNote.isPresent()){
            Nota nota = existingNote.get();
            nota.setContenido(body.getContenido());
            nota.setTitulo(body.getTitulo());
            return notaService.save(nota);
        }else{
            response.addHeader("errMsg", "La nota que quieres borrar no existe");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return new Nota();
        }
    }
    @PostMapping("/deleteNota")
    public void deleteNota (@RequestBody NotaDto body, HttpServletResponse response) throws IOException{
        Optional<Nota> existingNote = notaService.readNota(body.getId());
        if(existingNote.isPresent()){
            notaService.delete(existingNote.get());
        }else{
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.addHeader("errMsg", "La nota que quieres borrar no existe");
        }
    }
    @PostMapping("/createNota")
    public Nota createNota (@RequestBody NotaCreateDto notabody, HttpServletResponse response) throws IOException{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        try {
            User owner = userService.findOne(auth.getName());
            Nota nota = new Nota();
            nota.setTitulo(notabody.getTitulo());
            nota.setContenido(notabody.getContenido());
            nota.setUser(owner);
            return notaService.save(nota);
        } catch (Exception e) {
            //El usuario no existe
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.addHeader("errMsg", "La nota que quieres borrar no existe");
            return new Nota();
        }
    }
    @PostMapping("/readNota")
    public Nota readNota (@RequestBody NotaDto body, HttpServletResponse response) throws IOException{
        Optional<Nota> existingNote = notaService.readNota(body.getId());
        if(existingNote.isPresent()){
            return notaService.save(existingNote.get());
        }else{
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.addHeader("errMsg", "La nota que quieres borrar no existe");
            return new Nota();
        }
    }
    @GetMapping("/getInfo/{id}")
    public Optional<Nota> getInfo(@PathVariable(value = "id") long id){
        return notaService.readNota(id);
        //return userService.findUser(id);
    }
}