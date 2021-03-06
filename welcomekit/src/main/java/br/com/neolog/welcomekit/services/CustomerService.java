package br.com.neolog.welcomekit.services;

import static br.com.neolog.welcomekit.CustomerLocal.getCurrentCustomerId;
import static org.springframework.security.crypto.bcrypt.BCrypt.gensalt;
import static org.springframework.security.crypto.bcrypt.BCrypt.hashpw;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neolog.welcomekit.exceptions.customer.CustomerDuplicateEmailException;
import br.com.neolog.welcomekit.exceptions.customer.CustomerInvalidAccessException;
import br.com.neolog.welcomekit.exceptions.customer.CustomerNotFoundException;
import br.com.neolog.welcomekit.models.Customer;
import br.com.neolog.welcomekit.repository.CustomerRepository;

@Service
public class CustomerService
{
    @Autowired
    private CustomerRepository customerRepository;

    public Integer save(
        final Customer customer )
    {
        final Customer oldCustomer = customerRepository.findByEmailAndInactiveFalse( customer.getEmail() );
        if( oldCustomer != null ) {
            throw new CustomerDuplicateEmailException( "EMAIL=" + customer.getEmail() + " already exists" );
        }
        customer.setPassword( encrypt( customer.getPassword() ) );
        return customerRepository.save( customer ).getId();
    }

    public Customer update(
        final Customer customer )
    {
        if( ! customerRepository.existsById( customer.getId() ) ) {
            throw new CustomerNotFoundException( "ID=" + customer.getId() + " not exists" );
        }
        if( customer.getId() != getCurrentCustomerId() ) {
            throw new CustomerInvalidAccessException( "You can only change your account." );
        }
        return customerRepository.save( customer );
    }

    public Customer findByActiveEmail(
        final String email )
    {
        return customerRepository.findByEmailAndInactiveFalse( email );
    }

    public List<Customer> findAll()
    {
        return customerRepository.findAll();
    }

    public Customer inactivate()
    {
        final Customer customer = customerRepository.findById( getCurrentCustomerId() );
        if( customer == null ) {
            throw new CustomerNotFoundException( "ID=" + getCurrentCustomerId() + " not exists" );
        }
        customer.setInactive( true );
        return customerRepository.save( customer );
    }

    private static String encrypt(
        final String password )
    {
        return hashpw( password, gensalt() );
    }
}
