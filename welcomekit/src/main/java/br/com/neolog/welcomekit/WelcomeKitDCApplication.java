package br.com.neolog.welcomekit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication( exclude = {
    SecurityAutoConfiguration.class
} )
public class WelcomeKitDCApplication
{

    public static void main(
        final String[] args )
    {
        System.setProperty( "spring.devtools.restart.enabled", "true" );
        SpringApplication.run( WelcomeKitDCApplication.class, args );
    }
}
