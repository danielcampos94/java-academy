package br.com.neolog.welcomekit.exceptions.customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( code = HttpStatus.METHOD_NOT_ALLOWED )
public class CustomerDuplicateEmailException
    extends
        RuntimeException
{
    private static final long serialVersionUID = 6546810659183797703L;

    public CustomerDuplicateEmailException(
        final String message )
    {
        super( message );
    }
}
