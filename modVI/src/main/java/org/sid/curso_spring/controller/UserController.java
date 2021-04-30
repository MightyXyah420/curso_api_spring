package org.sid.curso_spring.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.sid.curso_spring.config.TokenProvider;
import org.sid.curso_spring.dto.UserDto;
import org.sid.curso_spring.model.LoginUser;
import org.sid.curso_spring.model.Nota;
import org.sid.curso_spring.model.User;
import org.sid.curso_spring.service.UserService;
import org.sid.curso_spring.service.impl.NotaServiceImpl;
import org.sid.curso_spring.service.impl.UserServiceImpl;
import org.sid.curso_spring.model.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private NotaServiceImpl notaService;

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto user, HttpServletResponse response) throws IOException{
        try {
            return userService.save(user);
        } catch (Exception e) {
            response.addHeader("errMsg", "Nombre de usuario en uso");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return new User();
        }
    }
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/admin", method = RequestMethod.GET)
    public String adminPing(){
        return "Sólo para admins";
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value="/user", method = RequestMethod.GET)
    public String userPing(){
        return "Sólo para usuarios";
    }
    
    
    @GetMapping("/getInfo")
    public User test(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userService.findOne(auth.getName());
        return userService.findOne(auth.getName());
    }
    @GetMapping("/getNotes")
    public List<Nota> testNotes(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User owner = userService.findOne(auth.getName());
        return notaService.test(owner);
    }
}