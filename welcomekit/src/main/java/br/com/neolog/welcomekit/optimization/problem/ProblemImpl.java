package br.com.neolog.welcomekit.optimization.problem;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import com.google.common.collect.ImmutableList;

import br.com.neolog.welcomekit.optimization.Item;

public final class ProblemImpl
    implements
        Problem
{
    private long targetValue;
    private List<Item> items;
    private Integer quantitySum;

    public static ProblemImpl create(
        final List<Item> problemItems,
        final long targetValue )
    {
        final ProblemImpl problem = new ProblemImpl();
        checkArgument( targetValue >= 0 );
        problem.items = ImmutableList.copyOf( problemItems );
        problem.targetValue = targetValue;
        return problem;
    }

    @Override
    public List<Item> getItems()
    {
        return items;
    }

    @Override
    public long getTargetValue()
    {
        return targetValue;
    }

    @Override
    public int getQuantitySum()
    {
        if( quantitySum == null ) {
            quantitySum = items.stream().mapToInt( item -> item.getQuantity() ).sum();
        }
        return quantitySum;
    }
}
