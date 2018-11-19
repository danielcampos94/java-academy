package br.com.neolog.welcomekit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neolog.welcomekit.optimization.problem.ProblemImpl;
import br.com.neolog.welcomekit.optimization.problem.ProblemFactory;
import br.com.neolog.welcomekit.optimization.recommendation.Recommendation;
import br.com.neolog.welcomekit.optimization.recommendation.RecommendationItem;
import br.com.neolog.welcomekit.optimization.solution.Solution;
import br.com.neolog.welcomekit.optimization.solution.SolutionConverter;
import br.com.neolog.welcomekit.optimization.solver.Solver;

@Service
public class OptimizationService
{
    @Autowired
    private ProblemFactory problemFactory;
    @Autowired
    private Solver solver;
    @Autowired
    private SolutionConverter solutionConverter;

    public Recommendation getRecommendation(
        final long targetValue )
    {
        final ProblemImpl problem = problemFactory.getProblem( targetValue );
        final Solution optimizedSolution = solver.optimize( problem );
        final List<RecommendationItem> recommendations = solutionConverter.toRecommendation( optimizedSolution );
        return new Recommendation( recommendations );
    }
}
