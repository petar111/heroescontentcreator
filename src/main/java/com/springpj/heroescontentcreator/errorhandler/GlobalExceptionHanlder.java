package com.springpj.heroescontentcreator.errorhandler;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class GlobalExceptionHanlder extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGlobalError(Exception e, WebRequest request){
		
		ErrorResponse response = ErrorResponse.builder()
				.withMessage(e.getMessage())
				.withStackTrace(ExceptionUtils.getStackTrace(e))
				.withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
				.build();
		
		return ResponseEntity.internalServerError().body(response);
		
		
	}

}
