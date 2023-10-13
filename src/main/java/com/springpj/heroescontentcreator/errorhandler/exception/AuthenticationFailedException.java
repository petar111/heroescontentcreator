package com.springpj.heroescontentcreator.errorhandler.exception;

public class AuthenticationFailedException extends ModelException {
    public AuthenticationFailedException(String message) {
        super(message);
    }
}
