package br.com.neolog.welcomekit.exceptions.customer;

public class CustomerInvalidPasswordException
    extends
        RuntimeException
{
    private static final long serialVersionUID = 7206436542936238199L;

    public CustomerInvalidPasswordException(
        final String message )
    {
        super( message );
    }
}
