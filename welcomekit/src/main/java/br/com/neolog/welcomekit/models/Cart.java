package br.com.neolog.welcomekit.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.google.common.base.MoreObjects;

@Entity
@Table( name = "cart" )
public class Cart
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "cart_sequence" )
    @SequenceGenerator( name = "cart_sequence", sequenceName = "cart_sequence", initialValue = 1, allocationSize = 1 )
    private Integer id;

    @NotNull( message = "this field is not null" )
    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "customer" )
    private Customer customer;

    @Column
    private double totalValue;

    @NotNull( message = "this field is not null" )
    @Enumerated( EnumType.STRING )
    private CartStatus cartStatus;

    public Cart()
    {
    }

    public Cart(
        final Customer customer,
        final double totalValue,
        final CartStatus cartStatus )
    {
        this.customer = customer;
        this.totalValue = totalValue;
        this.cartStatus = cartStatus;
    }

    public Integer getId()
    {
        return id;
    }

    public double getTotalValue()
    {
        return totalValue;
    }

    public void setTotalValue(
        final double totalValue )
    {
        this.totalValue = totalValue;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public CartStatus getCartStatus()
    {
        return cartStatus;
    }

    public void setCartStatus(
        final CartStatus cartStatus )
    {
        this.cartStatus = cartStatus;
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper( this )
            .add( "id", id )
            .add( "totalValue", totalValue )
            .add( "user", customer )
            .add( "cartStatus", cartStatus )
            .toString();
    }

    @Override
    public boolean equals(
        final Object obj )
    {
        if( this == obj ) {
            return true;
        }

        if( ! ( obj instanceof Cart ) ) {
            return false;
        }
        final Cart cart = (Cart) obj;
        return Objects.equals( id, cart.getId() )
            && Objects.equals( customer, cart.getCustomer() )
            && Objects.equals( cartStatus, cart.getCartStatus() );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, customer, cartStatus );
    }
}
