package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entites.User;
import com.educandoweb.course.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}") //vai aceitar apenas o id dentro da url
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping // faz um pre processamento, indicando que vai receber um post
	public ResponseEntity<User> insert(@RequestBody User obj) {
		obj = service.insert(obj);
		
		// no Postman (POST, body e raw)
		URI uri = ServletUriComponentsBuilder. // para deixar como Status 201 created, realiza esses comandos e mostra a localização da requisção
				  fromCurrentRequest().
				  path("/{id}").
				  buildAndExpand(obj.getId())
				  .toUri();		
		
		return ResponseEntity.created(uri).body(obj);
	}
}
