package br.com.neolog.welcomekit.optimization.solver;

import static com.google.common.collect.ImmutableList.toImmutableList;
import static java.util.Objects.requireNonNull;

import com.google.common.math.LongMath;

import br.com.neolog.welcomekit.optimization.Item;
import br.com.neolog.welcomekit.optimization.problem.Problem;
import br.com.neolog.welcomekit.optimization.problem.ProblemImpl;
import br.com.neolog.welcomekit.optimization.solution.Solution;

final class SimplifierSolver
    implements
        Solver
{
    private final Solver delegate;

    public SimplifierSolver(
        final Solver delegate )
    {
        this.delegate = requireNonNull( delegate );
    }

    @Override
    public Solution optimize(
        final Problem problem )
    {
        final long simplificationFactor = getSimplificationFactor( problem );
        final Solution simplifiedSolution = delegate.optimize( transform( problem, simplificationFactor ) );
        return inverseTransform( simplifiedSolution, simplificationFactor );
    }

    private static Solution inverseTransform(
        final Solution solution,
        final long simplificationFactor )
    {
        if( simplificationFactor == 1 ) {
            return solution;
        }
        return Solution.create( solution.getSolutionItems().stream()
            .map( item -> Item.create( item.getProductCode(), item.getPrice() * simplificationFactor, item.getQuantity() ) )
            .collect( toImmutableList() ) );
    }

    private static long getSimplificationFactor(
        final Problem problem )
    {
        long result = problem.getTargetValue();
        for( final Item item : problem.getItems() ) {
            result = LongMath.gcd( result, item.getPrice() );
        }
        return result;
    }

    private static Problem transform(
        final Problem problem,
        final long simplificationFactor )
    {
        if( simplificationFactor == 1 ) {
            return problem;
        }
        return ProblemImpl.create(
            problem.getItems().stream().map( item -> Item.create( item.getProductCode(), item.getPrice() / simplificationFactor,
                item.getQuantity() ) ).collect( toImmutableList() ),
            problem.getTargetValue() / simplificationFactor );
    }
}
