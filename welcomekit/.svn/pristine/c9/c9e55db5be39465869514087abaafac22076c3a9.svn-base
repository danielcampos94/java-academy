package br.com.neolog.welcomekit.optimization.recommendation;

import java.util.Collection;
import java.util.List;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;

public class Recommendation
{
    private final List<RecommendationItem> recommendationItems;
    private final long valueSum;

    public Recommendation(
        final Collection<RecommendationItem> list )
    {
        recommendationItems = ImmutableList.copyOf( list );
        valueSum = recommendationItems.stream().mapToLong( item -> item.getPrice() * item.getQuantity() ).sum();
    }

    public List<RecommendationItem> getRecommendationItems()
    {
        return recommendationItems;
    }

    public long getValueSum()
    {
        return valueSum;
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper( this )
            .add( "recommendationItems", recommendationItems )
            .add( "valueSum", valueSum )
            .toString();
    }
}
