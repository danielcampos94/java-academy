package br.com.neolog.welcomekit.models;

import static java.util.Objects.requireNonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.google.common.base.MoreObjects;

@Entity
@Table( name = "product_category" )
public class ProductCategory
{

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "product_category_sequence" )
    @SequenceGenerator( name = "product_category_sequence", initialValue = 1, allocationSize = 1 )
    private Integer id;

    @Column( unique = true )
    @NotNull( message = "this field is not null" )
    private Integer code;

    @Column( unique = true )
    @NotNull( message = "this field is not null" )
    private String name;

    public ProductCategory()
    {
    }

    public ProductCategory(
        final Integer code,
        final String name )
    {
        this.code = requireNonNull( code );
        this.name = requireNonNull( name );
    }

    public ProductCategory(
        final Integer id,
        final Integer code,
        final String name )
    {
        this.id = id;
        this.code = requireNonNull( code );
        this.name = requireNonNull( name );
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

    public String toString()
    {
        return MoreObjects.toStringHelper( ProductCategory.class )
            .add( "id", id )
            .add( "code", code )
            .add( "name", name ).toString();
    }

}
