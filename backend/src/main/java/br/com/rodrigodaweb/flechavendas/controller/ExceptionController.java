package br.com.rodrigodaweb.flechavendas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.rodrigodaweb.flechavendas.exception.BusinessException;
import br.com.rodrigodaweb.flechavendas.exception.ErrorResponse;

@ControllerAdvice
public class ExceptionController {

	static final Logger log = LoggerFactory.getLogger(ExceptionController.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		log.error("EXCEÇÃO NÃO TRATADA", ex);
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage("Erro na aplicação. Contacte o administrador!");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> businessExceptionHandler(BusinessException ex) {
		ErrorResponse error = new ErrorResponse();
		log.error("ERRO NA APLICAÇÃO", ex);
		error.setErrorCode(ex.getCodigoErro());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
