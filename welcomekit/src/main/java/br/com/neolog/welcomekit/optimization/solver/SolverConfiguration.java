package br.com.neolog.welcomekit.optimization.solver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolverConfiguration
{
    @Bean
    public Solver solver(final Solver route){
        return new SimplifierSolver( route );
    }
}
