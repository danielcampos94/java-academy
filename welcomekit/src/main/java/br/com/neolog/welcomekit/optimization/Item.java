package br.com.neolog.welcomekit.optimization;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.base.Equivalence;

public final class Item
{
    private int productCode;
    private long price;
    private int quantity;

    public static Item create(
        final int productCode,
        final long price,
        final int quantity )
    {
        final Item item = new Item();

        checkArgument( productCode > 0 );
        checkArgument( price > 0 );
        checkArgument( quantity > 0 );

        item.productCode = productCode;
        item.price = price;
        item.quantity = quantity;
        return item;
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

    /**
     * {@link Equivalence} que considera igualdade por {@link #productCode}.
     */
    public static final Equivalence<Item> productCodeEquivalence = new Equivalence<Item>() {
        @Override
        protected boolean doEquivalent(
            final Item a,
            final Item b )
        {
            return a.productCode == b.productCode;
        }

        @Override
        protected int doHash(
            final Item t )
        {
            return t.productCode;
        }
    };
}
