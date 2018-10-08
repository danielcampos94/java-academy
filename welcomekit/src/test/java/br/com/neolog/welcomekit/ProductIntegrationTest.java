package br.com.neolog.welcomekit;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;

public class ProductIntegrationTest
    extends
        AbstractIntegrationTest
{

    @Test
    public void shouldReturnStatusCreatedAndYourIdWhenAddNewProduct()
    {
        String json = "{\"code\": 20,	\"name\": \"TV\", \"price\": 2000.0,	\"description\":\"SMART TV 4K\", \"weight\":30.0 , \"category\":{ \"id\": 1, \"code\":10, \"name\":\"Eletronicos\"}}";

        Integer productId = given().contentType( ContentType.JSON ).body( json ).when().post( "/product/save" ).then().log().everything()
            .assertThat().statusCode( HttpStatus.SC_CREATED ).and().extract().response().as( Integer.class );

        assertThat( productId ).isEqualTo( 3 );
    }

    @Test
    public void shouldReturnStatusOkWhenGetSomeProduct()
    {
        given().contentType( ContentType.JSON ).when().get( "product/search/id/2" ).then().log().everything().assertThat()
            .statusCode( HttpStatus.SC_OK ).and().body( "id", Matchers.equalTo( 2 ) );
    }

    @Test
    public void shouldThrowProductNotFoundExceptionWhenUpdateProduct()
    {
        String json = "{\"id\": 30, \"code\": 20,	\"name\": \"TV\", \"price\": 2000.0,	\"description\":\"SMART TV 4K\", \"weight\":30.0 , \"category\":{ \"id\": 1, \"code\":10, \"name\":\"Eletronicos\"}}";
        given()
            .contentType( ContentType.JSON )
            .body( json ).when().put( "/product/update" )
            .then().log().everything()
            .assertThat()
            .statusCode( HttpStatus.SC_NOT_FOUND ).and()
            .body( "message", Matchers.equalTo( "ID=30 not exists" ) );
    }

    @Test
    public void shouldThrowProductDuplicateCodeExceptionWhenCreateProduct()
    {
        String json = "{\"code\": 11,	\"name\": \"TV\", \"price\": 2000.0,	\"description\":\"SMART TV 4K\", \"weight\":30.0 , \"category\":{ \"id\": 1, \"code\":10, \"name\":\"Eletronicos\"}}";
        given()
            .contentType( ContentType.JSON )
            .body( json ).when().post( "/product/save" )
            .then().log().everything()
            .assertThat()
            .statusCode( HttpStatus.SC_METHOD_NOT_ALLOWED ).and()
            .body( "message", Matchers.equalTo( "CODE=11 already exists" ) );

    }

    @Test
    public void shouldReturnStatusOkAndYourIdWhenDeleteProduct()
    {
        Integer deletedProductId = given()
        .contentType( ContentType.JSON )
        .when().delete("/product/remove/1")
        .then().log().everything()
        .assertThat()
        .statusCode( HttpStatus.SC_OK ).and()
        .extract().response().as( Integer.class );
        
        assertThat( deletedProductId ).isEqualTo( 1 );
        
    }

}
