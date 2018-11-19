package br.com.neolog.welcomekit.exceptions.stock;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( code = HttpStatus.METHOD_NOT_ALLOWED )
public class StockQuantityEmptyException
    extends
        RuntimeException
{
    private static final long serialVersionUID = - 1903243176583097636L;

    public StockQuantityEmptyException(
        final String message )
    {
        super( message );
    }
}
