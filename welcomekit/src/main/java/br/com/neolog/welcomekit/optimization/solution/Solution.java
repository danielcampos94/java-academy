package br.com.neolog.welcomekit.optimization.solution;

import java.util.Collection;
import java.util.List;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;

import br.com.neolog.welcomekit.optimization.Item;

public final class Solution
{
    private static final Solution emptySolution = Solution.create( ImmutableList.of() );

    private List<Item> solutionItems;
    private long valueSum;

    public static Solution create(
        final Collection<Item> list )
    {
        final Solution solution = new Solution();
        solution.solutionItems = ImmutableList.copyOf( list );
        solution.valueSum = solution.solutionItems.stream().mapToLong( item -> item.getPrice() * item.getQuantity() ).sum();
        return solution;
    }

    public static Solution empty()
    {
        return emptySolution;
    }

    public List<Item> getSolutionItems()
    {
        return solutionItems;
    }

    public long getValueSum()
    {
        return valueSum;
    }

    public boolean isFeasible(
        final long targetValue )
    {
        return valueSum <= targetValue;
    }

    public boolean holdsMoreValue(
        final Solution otherSolution )
    {
        return valueSum > otherSolution.getValueSum();
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper( this )
            .add( "solutionItems", solutionItems )
            .add( "valueSum", valueSum )
            .toString();
    }
}
