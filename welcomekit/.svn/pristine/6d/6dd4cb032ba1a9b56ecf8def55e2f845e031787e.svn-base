package br.com.neolog.welcomekit.exceptions.category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.METHOD_NOT_ALLOWED)
public class CategoryDuplicateCodeException extends RuntimeException {

	private static final long serialVersionUID = 2771500225820141761L;

	public CategoryDuplicateCodeException(final String message) {
		super(message);
	}
}
