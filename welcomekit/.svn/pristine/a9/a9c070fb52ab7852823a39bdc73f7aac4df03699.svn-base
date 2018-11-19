package br.com.neolog.welcomekit.optimization.recommendation;

import static java.util.Objects.requireNonNull;

public class RecommendationItem
{
    private String name;
    private int productCode;
    private long price;
    private int quantity;

    public static RecommendationItem create(
        final String name,
        final int productCode,
        final long price,
        final int quantity )
    {
        final RecommendationItem recommendation = new RecommendationItem();
        recommendation.name = requireNonNull( name );
        recommendation.productCode = productCode;
        recommendation.price = price;
        recommendation.quantity = quantity;
        return recommendation;
    }

    public String getName()
    {
        return name;
    }

    public int getProductCode()
    {
        return productCode;
    }

    public long getPrice()
    {
        return price;
    }

    public int getQuantity()
    {
        return quantity;
    }
}
