package br.com.neolog.welcomekit.exceptions.stock;

public class ProductAlreadyExistsException extends RuntimeException {

	
	private static final long serialVersionUID = 5637248171449062113L;

	public ProductAlreadyExistsException(final String message) {
		super(message);
	}
}
