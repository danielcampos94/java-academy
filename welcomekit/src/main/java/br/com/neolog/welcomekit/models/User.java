package br.com.neolog.welcomekit.models;

import static java.util.Objects.requireNonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.google.common.base.MoreObjects;

@Entity
@Table( name = "user" )
public class User
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "user_sequence" )
    @SequenceGenerator( name = "user_sequence", sequenceName = "user_sequence", initialValue = 1, allocationSize = 1 )
    private Integer id;

    @Column( unique = true )
    @NotBlank( message = "this field can not be empty or null" )
    @Email( )
    private String email;

    @Column( unique = true )
    @NotBlank( message = "this field can not be empty or null" )
    private String name;

    @Column( )
    @NotBlank( message = "this field can not be empty or null" )
    private String password;

    @Column( )
    private Boolean inactive;

    public User()
    {

    }

    public User(
        final String email,
        final String name,
        final String password,
        final Boolean inactive )
    {
        this.email = requireNonNull( email );
        this.name = requireNonNull( name );
        this.password = requireNonNull( password );
        this.inactive = inactive;
    }

    public User(
        final Integer id,
        final String email,
        final String name,
        final String password,
        final boolean inactive )
    {
        this.id = requireNonNull( id );
        this.email = requireNonNull( email );
        this.name = requireNonNull( name );
        this.password = requireNonNull( password );
        this.inactive = inactive;
    }

    public Integer getId()
    {
        return id;
    }

    public String getEmail()
    {
        return email;
    }

    public String getName()
    {
        return name;
    }

    public String getPassword()
    {
        return password;
    }

    public Boolean isInactive()
    {
        return inactive;
    }

    public String toString()
    {
        return MoreObjects.toStringHelper( this )
            .add( "id", id )
            .add( "email", email )
            .add( "name", name )
            .add( "password", password )
            .add( "inactive", inactive )
            .toString();
    }

}
