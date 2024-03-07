package com.educandoweb.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entites.User;
import com.educandoweb.course.repositories.UserRepositoty;

@Configuration
@Profile("test") //mesma config do app.properties
public class TestConfig implements CommandLineRunner{ // classe para instanciar o banco de dados

	@Autowired //resolve a dependia de injeção, e vai associar uma instancia de userRepository
	private UserRepositoty userRepositoty;

	@Override
	public void run(String... args) throws Exception { //executrado quando a aplicação for iniciada
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		userRepositoty.saveAll(Arrays.asList(u1, u2)); // salvar no banco e instaciar 
	}
	
	
}