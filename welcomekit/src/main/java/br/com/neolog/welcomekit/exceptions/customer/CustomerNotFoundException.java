package br.com.neolog.welcomekit.exceptions.customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( code = HttpStatus.NOT_FOUND )
public class CustomerNotFoundException
    extends
        RuntimeException
{
    private static final long serialVersionUID = - 7900001814181387613L;

    public CustomerNotFoundException(
        final String message )
    {
        super( message );
    }
}
