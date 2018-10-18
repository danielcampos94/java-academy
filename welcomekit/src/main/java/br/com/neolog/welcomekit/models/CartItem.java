package br.com.neolog.welcomekit.models;

import static com.google.common.base.MoreObjects.toStringHelper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table( name = "cart_item" )
public class CartItem
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "cart_item_sequence" )
    @SequenceGenerator( name = "cart_item_sequence", sequenceName = "cart_item_sequence", initialValue = 1, allocationSize = 1 )
    private Integer id;

    @NotNull( message = "this field is not null" )
    @OneToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "product" )
    private Product product;

    @Column
    @Min( 1 )
    private int quantity;

    @NotNull( message = "this field is not null" )
    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "cart" )
    private Cart cart;

    public Integer getId()
    {
        return id;
    }

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(
        final Product product )
    {
        this.product = product;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public Cart getCart()
    {
        return cart;
    }

    public void setCart(
        final Cart cart )
    {
        this.cart = cart;
    }

    @Override
    public String toString()
    {
        return toStringHelper( this )
            .add( "id", id )
            .add( "product", product )
            .add( "quantity", quantity )
            .add( "cart", cart )
            .toString();
    }
}
