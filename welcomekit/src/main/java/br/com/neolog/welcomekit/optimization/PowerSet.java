package br.com.neolog.welcomekit.optimization;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

public class PowerSet
{
    public static <T> Set<Set<T>> combine(
        final List<T> holder )
    {
        final Set<T> elements = new HashSet<>();
        elements.addAll( holder );
        return powerSet( elements );
    }

    private static <T> Set<Set<T>> powerSet(
        final Set<T> originalSet )
    {
        final ImmutableSet.Builder<Set<T>> setWithAllSets = ImmutableSet.builder();
        if( originalSet.isEmpty() ) {
            setWithAllSets.add( new HashSet<>() );
            return setWithAllSets.build();
        }
        final List<T> list = new ArrayList<>( originalSet );
        final T firstElement = list.get( 0 );
        final Set<T> setWithoutFirstElement = new HashSet<>( list.subList( 1, list.size() ) );
        for( final Set<T> set : powerSet( setWithoutFirstElement ) ) {
            final Set<T> newSet = ImmutableSet.<T>builder().add( firstElement ).addAll( set ).build();
            setWithAllSets.add( newSet );
            setWithAllSets.add( set );
        }
        return setWithAllSets.build();
    }
}
