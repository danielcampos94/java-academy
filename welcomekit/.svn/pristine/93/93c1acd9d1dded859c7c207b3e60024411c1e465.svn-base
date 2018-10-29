package br.com.neolog.welcomekit.models;

import com.google.common.base.MoreObjects;

public class CartItemHolder
{

    private int productCode;
    private int quantity;

    public CartItemHolder()
    {
    }

    public CartItemHolder(
        final int productCode,
        final int quantity )
    {
        this.productCode = productCode;
        this.quantity = quantity;
    }

    public int getProductCode()
    {
        return productCode;
    }

    public int getQuantity()
    {
        return quantity;
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper( this )
            .add( "productCode", productCode )
            .add( "quantity", quantity )
            .toString();
    }

}
