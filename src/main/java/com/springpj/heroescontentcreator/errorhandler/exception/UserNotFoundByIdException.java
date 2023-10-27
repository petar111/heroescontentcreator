package com.springpj.heroescontentcreator.errorhandler.exception;

public class UserNotFoundByIdException extends NotFoundException {

	private static final long serialVersionUID = 8295852147723526578L;
	
	public UserNotFoundByIdException(Long id) {
		super("User not found by id " + id + ".");
	}

}
