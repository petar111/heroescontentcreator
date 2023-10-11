package com.springpj.heroescontentcreator.errorhandler.exception;

public class OriginNotFoundByIdException extends NotFoundException {

	private static final long serialVersionUID = 8295852147723526578L;
	
	public OriginNotFoundByIdException(Long id) {
		super("Origin not found by id " + id + ".");
	}

}
