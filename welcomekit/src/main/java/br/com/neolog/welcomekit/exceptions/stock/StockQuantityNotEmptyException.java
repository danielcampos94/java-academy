package br.com.neolog.welcomekit.exceptions.stock;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.METHOD_NOT_ALLOWED)
public class StockQuantityNotEmptyException extends RuntimeException
{
    private static final long serialVersionUID = 7049397823514480105L;

    public StockQuantityNotEmptyException(final String message)
    {
        super(message);
    }
}
