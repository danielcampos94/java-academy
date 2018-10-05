package br.com.neolog.welcomekit.exceptions.category;

public class CategoryDuplicateCodeException extends RuntimeException {

	private static final long serialVersionUID = 2771500225820141761L;

	public CategoryDuplicateCodeException(final String message) {
		super(message);
	}
}
