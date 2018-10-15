package br.com.neolog.welcomekit.exceptions.category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.METHOD_NOT_ALLOWED)
public class CategoryNotEmptyException
    extends
        RuntimeException
{

    private static final long serialVersionUID = - 5429408886657881959L;

    public CategoryNotEmptyException(
        final String message )
    {
        super( message );
    }

}
