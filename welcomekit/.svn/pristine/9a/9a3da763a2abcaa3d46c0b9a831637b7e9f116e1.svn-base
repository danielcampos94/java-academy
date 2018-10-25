package br.com.neolog.welcomekit;

import static br.com.neolog.welcomekit.CartStatus.CANCELLED;
import static br.com.neolog.welcomekit.CartStatus.FINISHED;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.neolog.welcomekit.exceptions.ErrorResponse;
import br.com.neolog.welcomekit.models.Cart;
import br.com.neolog.welcomekit.models.CartItem;
import br.com.neolog.welcomekit.services.CartService;
import io.restassured.http.ContentType;

public class CartIntegrationTest
    extends
        AbstractIntegrationTest
{
    @Autowired
    private CartService cartService;

    @Test
    public void addItemShouldReturnStatusOKAndCreatedItemWhenAddNewItem()
    {
        final String json = "{\"product\":{\"code\":11}, \"quantity\":1000, \"cart\":{\"customer\":{\"email\":\"cart@teste.com.br\"}}}";

        final CartItem item = given().header( "token", "TOKENVALUE" ).contentType( ContentType.JSON ).body( json )
            .when().post( "/cart/add/item" )
            .then().log()
            .everything()
            .assertThat().statusCode( HttpStatus.SC_OK ).and().extract().response().as( CartItem.class );

        assertThat( item ).isNotNull();
    }

    @Test
    public void removeItemShouldReturnStatusOkAndVerifyIfItemWithProductCodeEquals10DoesNotExistWhenRemoveItem()
    {
        given().header( "token", "TOKENVALUE" ).contentType( ContentType.JSON )
            .when().put( "/cart/remove/item/code?productCode=10" )
            .then().log()
            .everything()
            .assertThat().statusCode( HttpStatus.SC_OK );

        final List<CartItem> listItens = cartService.findAllItens( 1 );

        assertThat( listItens ).extracting( "product" ).extracting( "code" ).doesNotContain( 10 );
    }

    @Test
    public void removeItemQuantityShouldReturnStatusOkAndVerifyIfQuantityIsDifferentFromInitialQuantityWhenRemoveQuantity()
    {
        final List<CartItem> listItens = cartService.findAllItens( 1 );

        final CartItem itemBeforeRemove = listItens.stream().filter( item -> item.getProduct().getCode() == 11 )
            .collect( Collectors.toList() ).get( 0 );

        final CartItem item = given().header( "token", "TOKENVALUE" ).contentType( ContentType.JSON )
            .when().put( "/cart/remove/item/code/quantity?productCode=11&quantity=100" )
            .then().log()
            .everything()
            .assertThat().statusCode( HttpStatus.SC_OK ).extract().response().as( CartItem.class );

        assertThat( item.getQuantity() ).isNotEqualTo( itemBeforeRemove.getQuantity() );
    }

    @Test
    public void searchAllByCartIdShouldReturnListOfCartItemNotEmptyWhenSearchByCartId()
    {
        final List<CartItem> listItens = given().header( "token", "TOKENVALUE" ).contentType( ContentType.JSON )
            .when().get( "/cart/search/all/cart/id?cartId=1" )
            .then().log()
            .everything()
            .assertThat().statusCode( HttpStatus.SC_OK ).extract().jsonPath().getList( "", CartItem.class );

        assertThat( listItens ).isNotEmpty();
    }

    @Test
    public void getCurrentPriceShouldReturnStatusOkAndTotalValueOfCart()
    {
        final Double price = given().header( "token", "TOKENVALUE" ).contentType( ContentType.JSON )
            .when().get( "cart/get/current/price" ).then().log().everything()
            .assertThat().statusCode( HttpStatus.SC_OK ).extract().body().as( Double.class );

        assertThat( price ).isNotNull();
        assertThat( price ).isEqualTo( 16020.0 );
    }

    @Test
    public void cancelShouldReturnStatusOkAndVerifyIfStatusChangeForCancelled()
    {
        final Cart cart = given().header( "token", "CANCELCART" ).contentType( ContentType.JSON )
            .when().put( "cart/cancel" ).then().log().everything()
            .assertThat().statusCode( HttpStatus.SC_OK ).extract().response().as( Cart.class );

        assertThat( cart.getCartStatus() ).isEqualTo( CANCELLED );
    }

    @Test
    public void checkoutShouldReturnForFirstCaseStatusOkAndCartStatusFinishedForSecondCaseThrowCartItemIllegalQuantityException()
    {
        final Cart checkoutCart = given().header( "token", "CHECKOUTCART" ).contentType( ContentType.JSON )
            .when().put( "cart/checkout" ).then().log().everything()
            .assertThat().statusCode( HttpStatus.SC_OK ).extract().response().as( Cart.class );

        assertThat( checkoutCart ).extracting( "cartStatus" ).contains( FINISHED );

        final ErrorResponse response = given().header( "token", "CHECKOUTCART2" ).contentType( ContentType.JSON )
            .when().put( "cart/checkout" ).then().log().everything()
            .assertThat().statusCode( HttpStatus.SC_METHOD_NOT_ALLOWED )
            .and().extract().as( ErrorResponse.class );
        
        assertThat( response.getMessage() ).contains( "Bola--> Not have this quantity in stock" );
    }
}
