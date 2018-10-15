package br.com.neolog.welcomekit.exceptions.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.NOT_FOUND )
public class ProductNotFoundException
    extends
        RuntimeException
{

    private static final long serialVersionUID = 7830727742535014635L;

    public ProductNotFoundException(
        final String message )
    {
        super( message );
    }
}
