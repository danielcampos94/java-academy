package br.com.neolog.welcomekit.models;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

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
@Table( name = "product" )
public class Product
{

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "product_sequence" )
    @SequenceGenerator( name = "product_sequence", sequenceName = "product_sequence", initialValue = 1, allocationSize = 1 )
    private Integer id;

    @Column( unique = true )
    @NotNull( message = "this field is not null" )
    private Integer code;

    @Column
    @NotNull( message = "this field is not null" )
    private String name;

    @Column
    @NotNull( message = "this field is not null" )
    private long price;

    @Column
    private String description;

    @Column
    private Long weight;

    @NotNull( message = "this field is not null" )
    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "category" )
    private ProductCategory category;

    public Product()
    {
    }

    public Product(
        final Integer code,
        final String name,
        final long price,
        final String descripition,
        final Long weight,
        final ProductCategory category )
    {
        this.code = requireNonNull( code );
        this.name = requireNonNull( name );
        this.price = Math.round( price * 10 * 10 );
        this.description = descripition;
        this.category = requireNonNull( category );
        this.weight = ( weight * 10 * 10 );
    }

    public Product(
        final Integer id,
        final Integer code,
        final String name,
        final long price,
        final String descripition,
        final Long weight,
        final ProductCategory category )
    {

        this.id = id;
        this.code = requireNonNull( code );
        this.name = requireNonNull( name );
        this.price = Math.round( price * 10 * 10 );
        this.description = descripition;
        this.category = requireNonNull( category );
        this.weight = ( weight * 10 * 10 );
    }

    public Integer getId()
    {
        return id;
    }

    public Integer getCode()
    {
        return code;
    }

    public String getName()
    {
        return name;
    }

    public double getPrice()
    {
        return price / 100;
    }

    public String getDescription()
    {
        return description;
    }

    public ProductCategory getCategory()
    {
        return category;
    }

    public double getWeight()
    {
        return weight / 100;
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper( this )
            .add( "id", id )
            .add( "code", code )
            .add( "name", name )
            .add( "price", price )
            .add( "description", description )
            .add( "weight", weight )
            .add( "category", category ).toString();
    }

    @Override
    public boolean equals(
        final Object obj )
    {
        if( this == obj ) {
            return true;
        }

        if( ! ( obj instanceof Product ) ) {
            return false;
        }
        final Product product = (Product) obj;
        return Objects.equals( id, product.getId() )
            && Objects.equals( code, product.getCode() )
            && Objects.equals( name, product.getName() )
            && Objects.equals( price, product.getPrice() )
            && Objects.equals( description, product.getDescription() )
            && Objects.equals( weight, product.getWeight() );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, code, name, price, description, weight );
    }
}
