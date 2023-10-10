package com.springpj.heroescontentcreator.errorhandler;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
	
	private final HttpStatus status;
	private final String message;
	private final String stackTrace;
	
	
	
	private ErrorResponse(ErrorResponseBuilder builder) {
		this.status = builder.status;
		this.message = builder.message;
		this.stackTrace = builder.stackTrace;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
	public String getStackTrace() {
		return stackTrace;
	}
	
	public static ErrorResponseBuilder builder() {
		return new ErrorResponseBuilder();
	}
	
	public static class ErrorResponseBuilder{
		
		private HttpStatus status;
		private String message;
		private String stackTrace;
		
		public ErrorResponseBuilder withStatus(HttpStatus status) {
			this.status = status;
			return this;
		}
		
		public ErrorResponseBuilder withMessage(String message) {
			this.message = message;
			return this;
		}
		
		public ErrorResponseBuilder withStackTrace(String stackTrace) {
			this.stackTrace = stackTrace;
			return this;
		}
		
		public ErrorResponse build() {
			return new ErrorResponse(this);
		}
		
	}
	
	
	
}
