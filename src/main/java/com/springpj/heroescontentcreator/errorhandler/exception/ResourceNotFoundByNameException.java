package com.springpj.heroescontentcreator.errorhandler.exception;

public class ResourceNotFoundByNameException extends NotFoundException {

	public ResourceNotFoundByNameException(String name) {
		super("Resource not found: " + name);
	}
	
}
