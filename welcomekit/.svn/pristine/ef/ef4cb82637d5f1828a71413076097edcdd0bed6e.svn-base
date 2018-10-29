package br.com.neolog.welcomekit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication( exclude = {
    SecurityAutoConfiguration.class
} )
public class WelcomeKitDCApplication
{

    public static void main(
        final String[] args )
    {
        SpringApplication.run( WelcomeKitDCApplication.class, args );
    }
}
