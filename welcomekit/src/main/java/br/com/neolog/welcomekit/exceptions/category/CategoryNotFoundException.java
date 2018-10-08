package br.com.neolog.welcomekit.exceptions.category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class CategoryNotFoundException
    extends
        RuntimeException
{
    private static final long serialVersionUID = 2765561791067921415L;

    public CategoryNotFoundException(
       final String message )
    {
        super( message );
    }
}
