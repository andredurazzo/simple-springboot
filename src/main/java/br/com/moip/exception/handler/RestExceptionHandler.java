package br.com.moip.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.moip.exception.BusinessException;
import br.com.moip.response.Response;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public <T> ResponseEntity<Response<T>> exceptionBusinessHandler(Exception ex) {
		Response<T> error = new Response<T>();
		error.setMessage(ex.getMessage());
		error.setSuccess(false);
		return new ResponseEntity<Response<T>>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public <T> ResponseEntity<Response<T>> exceptionHandler(Exception ex) {
		Response<T> error = new Response<T>();
		error.setMessage(ex.getMessage());
		error.setSuccess(false);
		return new ResponseEntity<Response<T>>(error, HttpStatus.NOT_FOUND);
	}

}
