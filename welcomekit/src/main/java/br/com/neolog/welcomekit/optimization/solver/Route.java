package br.com.neolog.welcomekit.optimization.solver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.com.neolog.welcomekit.optimization.problem.Problem;
import br.com.neolog.welcomekit.optimization.solution.Solution;

@Component
@Primary
final class Route
    implements
        Solver
{
    private final Solver heuristic;
    private final Solver exact;

    @Autowired
    public Route(
        @Qualifier( "heuristic" ) final Solver heuristic,
        @Qualifier( "exact" ) final Solver exact )
    {
        this.heuristic = heuristic;
        this.exact = exact;
    }

    @Override
    public Solution optimize(
        final Problem problem )
    {
        if( problem.getQuantitySum() >= 22 ) {
            return heuristic.optimize( problem );
        }
        return exact.optimize( problem );
    }

}
