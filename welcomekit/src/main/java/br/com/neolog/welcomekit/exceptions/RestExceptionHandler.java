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

//	@ExceptionHandler(CategoryNotFoundException.class)
//	public final ResponseEntity<ErrorDetails> handleCategoryNotFoundException(final CategoryNotFoundException e,
//			final WebRequest request) {
//		final ErrorDetails error = new ErrorDetails(new Date(), HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(),
//				e.getClass().getName(), e.getMessage(), request.getDescription(false));
//		LoggerMaster.warning(error.toString());
//		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//	}

	private String bindingResultToString(final MethodArgumentNotValidException e) {

		final StringBuilder stringBuilder = new StringBuilder("Validation failed. Error(s) details: ");
		for (final ObjectError error : e.getBindingResult().getAllErrors()) {
			final String[] field = error.getCodes();
			stringBuilder.append("[").append(field[0]).append(": ").append(error.getDefaultMessage()).append("] ");
		}
		return stringBuilder.toString();
	}

}
