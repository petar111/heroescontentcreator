package com.springpj.heroescontentcreator.errorhandler;

import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springpj.heroescontentcreator.errorhandler.ErrorResponse.ErrorResponseBuilder;
import com.springpj.heroescontentcreator.errorhandler.exception.AuthenticationFailedException;
import com.springpj.heroescontentcreator.errorhandler.exception.NotFoundException;

import jakarta.validation.ConstraintViolationException;


@RestControllerAdvice
public class GlobalExceptionHanlder extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGlobalError(Exception e, WebRequest request){
		return composeDefaultErrorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> handleGlobalError(NotFoundException e, WebRequest request){
		return composeDefaultErrorResponse(e, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleGlobalError(ConstraintViolationException e, WebRequest request){	
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = e.getConstraintViolations()
			.stream().map(c -> c.getMessage()).collect(Collectors.joining("\n"));
		
		ErrorResponse response = composeErrorResponseBuilder(e, status)
				.withMessage(message).build();
		
		return ResponseEntity.status(status).body(response);
	}
	
	@ExceptionHandler(AuthenticationFailedException.class)
	public ResponseEntity<ErrorResponse> handleAuthFailedException(AuthenticationFailedException e, WebRequest request){
		return composeDefaultErrorResponse(e, HttpStatus.UNAUTHORIZED);
	}
	
	
	private ErrorResponseBuilder composeErrorResponseBuilder(Exception e, HttpStatus status){
		
		return ErrorResponse.builder()
				.withMessage(e.getMessage())
				.withDetailedMessage(e.getMessage())
				.withStackTrace(ExceptionUtils.getStackTrace(e))
				.withStatus(status);
	}
	
	private ResponseEntity<ErrorResponse> composeDefaultErrorResponse(Exception e, HttpStatus status){
		return ResponseEntity.status(status).body(
										composeErrorResponseBuilder(e, status).build());
	}

}
