package br.com.neolog.welcomekit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.neolog.welcomekit.optimization.recommendation.Recommendation;
import br.com.neolog.welcomekit.services.OptimizationService;

@RestController
@RequestMapping( value = "/recommendation", produces = MediaType.APPLICATION_JSON_VALUE )
public class OptimizationController
{
    @Autowired
    private OptimizationService optimizationService;

    @GetMapping
    public Recommendation getResult(
        @RequestParam( "target" ) final long target )
    {
        return optimizationService.getRecommendation( target );
    }
}
