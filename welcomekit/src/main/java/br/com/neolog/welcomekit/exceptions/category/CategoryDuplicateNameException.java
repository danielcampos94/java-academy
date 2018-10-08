package br.com.neolog.welcomekit.exceptions.category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.METHOD_NOT_ALLOWED)
public class CategoryDuplicateNameException extends RuntimeException {

	private static final long serialVersionUID = -7362226794392409585L;

	public CategoryDuplicateNameException(final String message) {
		super(message);
	}
}
