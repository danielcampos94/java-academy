package br.com.neolog.welcomekit.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.neolog.welcomekit.models.Customer;
import br.com.neolog.welcomekit.services.CustomerService;

@RestController
@RequestMapping( value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE )
public class CustomerController
{
    @Autowired
    private CustomerService customerSevice;

    @PostMapping( "/save" )
    public ResponseEntity<Integer> save(
        @RequestBody @Valid final Customer customer )
    {
        final Integer idUser = customerSevice.save( customer );
        return new ResponseEntity<>( idUser, HttpStatus.CREATED );
    }

    @PutMapping( "/update" )
    public ResponseEntity<Customer> update(
        @RequestBody @Valid final Customer customer )
    {
        customerSevice.update( customer );
        return new ResponseEntity<>( customer, HttpStatus.OK );
    }

    @GetMapping( "search/email" )
    public ResponseEntity<Customer> findByEmail(
        @RequestParam( "email" ) final String email )
    {
        final Customer customer = customerSevice.findByActiveEmail( email );
        return new ResponseEntity<>( customer, HttpStatus.OK );
    }

    @GetMapping( "search/all" )
    public List<Customer> findAll()
    {
        return customerSevice.findAll();
    }

    @PutMapping( "inactivate/email" )
    public ResponseEntity<String> inactivate(
        @RequestParam( "email" ) final String email )
    {
        customerSevice.inactivate( email );
        return new ResponseEntity<>( email + " inactive", HttpStatus.OK );
    }
}
