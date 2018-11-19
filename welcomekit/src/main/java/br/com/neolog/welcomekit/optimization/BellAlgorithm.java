package br.com.neolog.welcomekit.optimization;

import static com.google.common.collect.ImmutableList.toImmutableList;
import static com.google.common.collect.Multisets.toMultiset;
import static java.util.Objects.requireNonNull;
import static java.util.function.Function.identity;

import java.util.List;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;

import br.com.neolog.welcomekit.optimization.problem.Problem;
import br.com.neolog.welcomekit.optimization.solution.Solution;
import br.com.neolog.welcomekit.optimization.solver.Solver;

public class BellAlgorithm
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

        final List<Item> items = ImmutableList.copyOf( split( problem.getItems() ) );
        
        return null;
    }

    /**
     * Retorna uma nova lista que, para cada {@link Item} com quantidade Q são
     * criados Q instâncias de um item com mesmas características, porém
     * quantidade 1.
     *
     * @see #join(List) para realizar o processo contrário
     * @throws NullPointerException caso problemItems sera <code>null</code> ou
     *         contenha elemento <code>null</code>
     */
    private static List<Item> split(
        final List<Item> problemItems )
    {
        final ImmutableList.Builder<Item> builder = ImmutableList.builder();
        for( final Item problemItem : problemItems ) {
            for( int i = 0; i < problemItem.getQuantity(); i++ ) {
                builder.add( Item.create( problemItem.getProductCode(), problemItem.getPrice(), 1 ) );
            }
        }
        return builder.build();
    }

    private static List<Item> join(
        final List<Item> itemsToJoin )
    {
        return itemsToJoin.stream().map( Item.productCodeEquivalence::wrap )
            .collect( toMultiset( identity(), wrapper -> wrapper.get().getQuantity(), HashMultiset::create ) )
            .entrySet().stream()
            .map( entry -> {
                final Item item = entry.getElement().get();
                return Item.create( item.getProductCode(), item.getPrice(), entry.getCount() );
            } )
            .collect( toImmutableList() );
    }
}

