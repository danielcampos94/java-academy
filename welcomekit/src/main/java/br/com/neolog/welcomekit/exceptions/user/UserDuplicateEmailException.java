package br.com.neolog.welcomekit.exceptions.user;

public class UserDuplicateEmailException extends RuntimeException
{
    private static final long serialVersionUID = 6546810659183797703L;

    public UserDuplicateEmailException(final String message)
    {
        super(message);
    }
}
