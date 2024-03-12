package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entites.User;
import com.educandoweb.course.repositories.UserRepositoty;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service //registra a classe como componente do Spring
public class UserService {
	
	@Autowired
	private UserRepositoty repositoty;
	
	public List<User> findAll(){
		return repositoty.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repositoty.findById(id);
		
		return obj.orElseThrow(() -> new ResourceNotFoundException(id)); // retorna o objeto do tipo user que estiver dentro do optional
	}
	
	public User insert(User obj) {
		return repositoty.save(obj); // implementação para salvar no banco de dados um nov obj do tipo user
	}
	
	public void delete(Long id) {
		try {
			repositoty.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public User update(Long id, User obj) {
		try {
			User entity = repositoty.getReferenceById(id);
			updateData(entity, obj);
			
			return repositoty.save(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
