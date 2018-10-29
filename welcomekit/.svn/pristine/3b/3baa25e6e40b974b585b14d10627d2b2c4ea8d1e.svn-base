package br.com.neolog.welcomekit.exceptions.customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( code = HttpStatus.METHOD_NOT_ALLOWED )
public class CustomerInvalidAccessException
    extends
        RuntimeException
{
    private static final long serialVersionUID = 2535433270797111285L;

    public CustomerInvalidAccessException(
        final String message )
    {
        super( message );
    }
}
