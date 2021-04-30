package org.sid.curso_spring;

import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

import org.sid.curso_spring.dto.UserDto;
import org.sid.curso_spring.model.Role;
import org.sid.curso_spring.model.User;
import org.sid.curso_spring.service.RoleService;
import org.sid.curso_spring.service.impl.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CursoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursoSpringApplication.class, args);
	}
	@Bean
	public CommandLineRunner initApp(UserServiceImpl userService, RoleService roleService){
		return args -> {
			//todo
			Role user = new Role();
			user.setDescription("Rol para los usuarios");
			user.setName("USER");
			Role admin = new Role();
			admin.setDescription("Rol para los administradores");
			admin.setName("ADMIN");
			Role a = roleService.save(user);
			Role b = roleService.save(admin);
			UserDto adminUser = new UserDto();
			adminUser.setName("Juan");
			adminUser.setPassword("123");
			adminUser.setUsername("admin");
			Set<Role> roleSet = new HashSet<>();
			roleSet.add(a);
			roleSet.add(b);
			User savedAdmin = userService.CreateAdmin(adminUser, roleSet);
			System.out.println("Creó que funcionó");
			//Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
        	//String entradaTeclado = entradaEscaner.nextLine (); //Invocamos un método sobre un objeto Scanner
        	//System.out.println ("Entrada recibida por teclado es: \"" + entradaTeclado +"\"");
		};
	}
}
