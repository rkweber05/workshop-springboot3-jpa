package com.educandoweb.course.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object id) { // tentar passar o id do obj que eu tentar encontrar, mas não encontrei
		super("Resource not found. Id " + id);
	}
	
}
