package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entites.User;

public interface UserRepositoty extends JpaRepository<User, Long> { //funções para trabalhar com o usuario

	
}
