package br.com.neolog.welcomekit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.neolog.welcomekit.services.SessionService;

@RestController
@RequestMapping( value = "/session", produces = MediaType.APPLICATION_JSON_VALUE )
public class SessionController
{
    @Autowired
    private SessionService sessionService;

    @PostMapping( "/login" )
    public ResponseEntity<String> login(
        @RequestParam( "email" ) final String email,
        @RequestParam( "password" ) final String password )
    {
        return ResponseEntity.ok().body( sessionService.login( email, password ) );
    }

    @PostMapping( "/logout" )
    public void logout(
        @RequestParam( "token" ) final String token )
    {
        sessionService.logout( token );
    }
}
