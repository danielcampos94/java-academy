package br.com.neolog.welcomekit.exceptions.item;

public class CartItemIllegalQuantityException
    extends
        RuntimeException
{
    private static final long serialVersionUID = 2586675465681560839L;

    public CartItemIllegalQuantityException(final String message)
    {
        super(message);
    }
}
