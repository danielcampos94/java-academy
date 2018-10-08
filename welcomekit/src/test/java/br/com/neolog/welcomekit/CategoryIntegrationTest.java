package br.com.neolog.welcomekit;


import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;

public class CategoryIntegrationTest
    extends
        AbstractIntegrationTest
{

    @Test
    public void shouldReturnStatusCreatedWhenAddNewCategory()
    {
        String json = "{\"code\":50, \"name\":\"Livros\"}";

        Integer categoryId = given().contentType( ContentType.JSON ).body( json ).when().post( "/category/save" ).then().log().everything()
            .assertThat().statusCode( HttpStatus.SC_CREATED ).and().extract().response().as( Integer.class );

        assertThat( categoryId ).isEqualTo( 3 );
    }
    
    @Test
    public void shouldThrowCategoryNotFoundExceptionWhenUpdateCategory()
    {
        String json = "{ \"id\": 30, \"code\":10, \"name\":\"Eletronicos\"}";
        given()
            .contentType( ContentType.JSON )
            .body( json ).when().put( "/category/update" )
            .then().log().everything()
            .assertThat()
            .statusCode( HttpStatus.SC_NOT_FOUND ).and()
            .body( "message", Matchers.equalTo( "ID=30 not exists" ) );
    }

    @Test
    public void shouldThrowCategoryDuplicateCodeExceptionWhenCreateProduct()
    {
        String json = "{ \"code\":10, \"name\":\"Lazer\"}";
        given()
            .contentType( ContentType.JSON )
            .body( json ).when().post( "/category/save" )
            .then().log().everything()
            .assertThat()
            .statusCode( HttpStatus.SC_METHOD_NOT_ALLOWED ).and()
            .body( "message", Matchers.equalTo( "CODE=10 already exists" ) );

    }
   
    @Test
    public void shouldThrowCategoryDuplicateNameExceptionWhenCreateProduct()
    {
        String json = "{ \"code\":654, \"name\":\"Eletronicos\"}";
        given()
        .contentType( ContentType.JSON )
        .body( json ).when().post( "/category/save" )
        .then().log().everything()
        .assertThat()
        .statusCode( HttpStatus.SC_METHOD_NOT_ALLOWED ).and()
        .body( "message", Matchers.equalTo( "NAME=Eletronicos already exists" ) );
        
    }
}
