package br.com.neolog.welcomekit.exceptions;


import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(final MethodArgumentNotValidException exception, final WebRequest request) {
		final ErrorResponse errorResponse = new ErrorResponse(new Date(), bindingResultToString(exception), HttpStatus.BAD_REQUEST.value(), 
				HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

	private String bindingResultToString(final MethodArgumentNotValidException e) {

		final StringBuilder stringBuilder = new StringBuilder("Validation failed. Error(s) details: ");
		for (final ObjectError error : e.getBindingResult().getAllErrors()) {
			final String[] field = error.getCodes();
			stringBuilder.append("[").append(field[0]).append(": ").append(error.getDefaultMessage()).append("] ");
		}
		return stringBuilder.toString();
	}

}
