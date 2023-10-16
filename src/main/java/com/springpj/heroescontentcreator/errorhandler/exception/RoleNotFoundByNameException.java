package com.springpj.heroescontentcreator.errorhandler.exception;

public class RoleNotFoundByNameException extends NotFoundException{

	public RoleNotFoundByNameException(String name) {
		super("Role not found: " + name);
	}
}
