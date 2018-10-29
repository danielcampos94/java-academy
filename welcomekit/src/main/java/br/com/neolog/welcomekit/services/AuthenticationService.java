package br.com.neolog.welcomekit.services;

import static java.util.Objects.requireNonNull;

import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import br.com.neolog.welcomekit.exceptions.customer.CustomerInvalidPasswordException;
import br.com.neolog.welcomekit.exceptions.customer.CustomerNotFoundException;
import br.com.neolog.welcomekit.models.Customer;
import br.com.neolog.welcomekit.models.Session;
import br.com.neolog.welcomekit.repository.SessionRepository;

@Service
public class AuthenticationService
{
    @Autowired
    private CustomerService customerService;

    @Autowired
    private SessionRepository sessionRepository;

    public String login(
        final String email,
        final String password )
    {
        final Customer customer = customerService.findByActiveEmail( email );
        if( customer == null ) {
            throw new CustomerNotFoundException( "This customer not exists" );
        }
        if( ! BCrypt.checkpw( password, customer.getPassword() ) ) {
            throw new CustomerInvalidPasswordException( "Invalid password" );
        }
        final String token = getToken();
        final DateTime now = DateTime.now();
        sessionRepository.save( new Session( token, now.plusHours( 2 ), now, customer ) );
        return token;
    }

    public void logout(
        final String token )
    {
        requireNonNull( token );
        final Session session = sessionRepository.findByToken( token );
        session.setExpirationDate( DateTime.now() );
        sessionRepository.save( session );
    }

    private String getToken()
    {
        return UUID.randomUUID().toString();
    }
}
