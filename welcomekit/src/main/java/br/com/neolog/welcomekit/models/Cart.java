package br.com.neolog.welcomekit.models;

import javax.persistence.Column;
import javax.persistence.Entity;
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

//    @NotNull( message = "this field is not null" )
//    @ManyToOne( fetch = FetchType.EAGER )
//    @JoinColumn( name = "user" )
//    private Product product;

    @Column
    private long totalValue;

    @NotNull( message = "this field is not null" )
    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "user" )
    private User user;

    @NotNull( message = "this field is not null" )
    private CartStatus cartStatus;

    public Integer getId()
    {
        return id;
    }

//    public Product getProduct()
//    {
//        return product;
//    }

    public long getTotalValue()
    {
        return totalValue;
    }

    public User getUser()
    {
        return user;
    }

    public CartStatus getCartStatus()
    {
        return cartStatus;
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper( this )
            .add( "id", id )
            // .add( "product", product )
            .add( "totalValue", totalValue )
            .add( "user", user )
            .add( "cartStatus", cartStatus )
            .toString();
    }

}
