package com.springpj.heroescontentcreator.errorhandler.exception;

public class UserNotFoundByUsernameException extends NotFoundException{
	
	public UserNotFoundByUsernameException(String username) {
		super("User not found by username " + username + ".");
	}

}
