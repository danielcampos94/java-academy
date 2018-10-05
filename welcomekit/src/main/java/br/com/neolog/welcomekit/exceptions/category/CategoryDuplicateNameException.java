package br.com.neolog.welcomekit.exceptions.category;

public class CategoryDuplicateNameException extends RuntimeException {

	private static final long serialVersionUID = -7362226794392409585L;

	public CategoryDuplicateNameException(final String message) {
		super(message);
	}
}
