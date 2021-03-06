package br.com.neolog.welcomekit.exceptions.item;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( code = HttpStatus.METHOD_NOT_ALLOWED )
public class CartItemIllegalQuantityException
    extends
        RuntimeException
{
    private static final long serialVersionUID = 2586675465681560839L;

    List<String> item = new ArrayList<>();

    public CartItemIllegalQuantityException(
        final String message )
    {
        super( message );
        item.add( message );
    }

    public CartItemIllegalQuantityException(
        final List<String> message )
    {
        item.addAll( message );
    }

    public List<String> getItem()
    {
        return item;
    }
}
