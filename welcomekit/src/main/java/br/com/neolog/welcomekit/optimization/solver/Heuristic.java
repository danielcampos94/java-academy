package br.com.neolog.welcomekit.optimization.solver;

import static java.util.Objects.requireNonNull;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.neolog.welcomekit.optimization.Item;
import br.com.neolog.welcomekit.optimization.problem.Problem;
import br.com.neolog.welcomekit.optimization.solution.Solution;

@Component
@Qualifier( "heuristic" )
final class Heuristic
    implements
        Solver
{
    @Override
    public Solution optimize(
        final Problem problem )
    {
        requireNonNull( problem );

        if( problem.getItems().isEmpty() ) {
            return Solution.empty();
        }

        final Solution solution = Solution.create( problem.getItems() );
        if( solution.isFeasible( problem.getTargetValue() ) ) {
            return solution;
        }

        final List<Item> bestCombinationItems = new LinkedList<>();
        long bestPrice = 0;

        for( final Item problemItem : problem.getItems() ) {
            final long longPrice = problemItem.getPrice();
            long currentTarget = problem.getTargetValue();
            currentTarget -= bestPrice;
            final int quantity = Long.valueOf( currentTarget / longPrice ).intValue();

            if( isLessThanZero( quantity ) ) {
                continue;
            }
            if( bestPrice <= problem.getTargetValue() ) {
                if( quantity <= problemItem.getQuantity() ) {
                    bestCombinationItems.add( Item.create( problemItem.getProductCode(), longPrice, quantity ) );
                    bestPrice += ( quantity * longPrice );
                } else {
                    bestCombinationItems.add( Item.create( problemItem.getProductCode(), longPrice, problemItem.getQuantity() ) );
                    bestPrice += ( problemItem.getQuantity() * longPrice );
                }
            }
        }
        return Solution.create( bestCombinationItems );
    }

    private boolean isLessThanZero(
        final int quantity )
    {
        if( quantity < 1 ) {
            return true;
        }
        return false;
    }

}