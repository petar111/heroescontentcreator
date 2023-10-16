package com.springpj.heroescontentcreator.errorhandler.exception;

public class AccessTypeNotFoundByNameException  extends NotFoundException{
	
	public AccessTypeNotFoundByNameException(String name) {
		super("Access type not found: " + name);
	}

}
