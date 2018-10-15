package br.com.neolog.welcomekit.exceptions.user;

public class UserNotFoundException
    extends
        RuntimeException
{
    private static final long serialVersionUID = - 7900001814181387613L;

    public UserNotFoundException(final String message)
    {
        super(message);
    }
}
