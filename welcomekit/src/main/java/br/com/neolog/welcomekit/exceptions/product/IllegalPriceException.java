package br.com.neolog.welcomekit.exceptions.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_ACCEPTABLE)
public class IllegalPriceException extends RuntimeException
{
    private static final long serialVersionUID = - 2071782665131770724L;

    public IllegalPriceException(final String message)
    {
        super(message);
    }
}
