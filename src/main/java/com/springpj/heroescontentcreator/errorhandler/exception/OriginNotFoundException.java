package com.springpj.heroescontentcreator.errorhandler.exception;

public class OriginNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8295852147723526578L;
	
	public OriginNotFoundException(Long id) {
		super("Origin not found by id " + id + ".");
	}

}
