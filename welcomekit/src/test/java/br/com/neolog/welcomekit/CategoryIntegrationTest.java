package br.com.neolog.welcomekit;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.neolog.welcomekit.models.ProductCategory;
import br.com.neolog.welcomekit.services.SessionService;
import io.restassured.http.ContentType;

public class CategoryIntegrationTest
    extends
        AbstractIntegrationTest
{
    @Autowired
    private SessionService sessionService;

    @Test
    public void shouldReturnStatusCreatedWhenAddNewCategory()
    {
        final String json = "{\"code\":50, \"name\":\"Livros\"}";

        final Integer categoryId = given().contentType( ContentType.JSON ).body( json ).when().post( "/category/save" ).then().log()
            .everything()
            .assertThat().statusCode( HttpStatus.SC_CREATED ).and().extract().response().as( Integer.class );

        assertThat( categoryId ).isEqualTo( 3 );
    }

    @Test
    public void shouldReturnStatusOkAndVerifyUpdateCategory()
    {
        final String json = "{\"id\": 1, \"code\":80, \"name\":\"Eletrodomesticos\"}";

        final String token = sessionService.login( "teste@teste.com.br", "teste123" );
        final ProductCategory category = given().header( "token", token ).contentType( ContentType.JSON ).body( json ).when().put( "/category/update" ).then().log()
            .everything()
            .assertThat().statusCode( HttpStatus.SC_OK ).and().extract().response().as( ProductCategory.class );

        final ProductCategory updatedCategory = new ProductCategory( 1, 80, "Eletrodomesticos" );
        assertThat( category ).isEqualTo( updatedCategory );
    }

    @Test
    public void shouldReturnStatusOkWhenGetCategoryById()
    {
        given().contentType( ContentType.JSON ).when().get( "category/search/id/1" ).then().log().everything().assertThat()
            .statusCode( HttpStatus.SC_OK ).and().body( "id", Matchers.equalTo( 1 ) );
    }

    @Test
    public void shouldReturnStatusOkWhenGetCategoryByCode()
    {
        given().contentType( ContentType.JSON ).when().get( "category/search/code/10" ).then().log().everything().assertThat()
            .statusCode( HttpStatus.SC_OK ).and().body( "code", Matchers.equalTo( 10 ) );
    }

    @Test
    public void shouldReturnStatusOkWhenGetAllCategories()
    {
        final List<ProductCategory> list = given().contentType( ContentType.JSON ).when().get( "category/search/all" ).then().log()
            .everything().assertThat()
            .statusCode( HttpStatus.SC_OK ).and().extract().jsonPath().getList( "", ProductCategory.class );

        assertThat( list ).isNotEmpty();
    }

    @Test
    public void shouldReturnStatusOkWhenDeleteCategoryByCode()
    {
        final Integer deletedCategoryId = given()
            .contentType( ContentType.JSON )
            .when().delete( "/category/remove/50" )
            .then().log().everything()
            .assertThat()
            .statusCode( HttpStatus.SC_OK ).and()
            .extract().response().as( Integer.class );

        assertThat( deletedCategoryId ).isEqualTo( 50 );

    }

    @Test
    public void shouldThrowCategoryNotFoundExceptionWhenDeleteCategory()
    {
        given()
            .contentType( ContentType.JSON )
            .when().delete( "/category/remove/631" )
            .then().log().everything()
            .assertThat()
            .statusCode( HttpStatus.SC_NOT_FOUND ).and()
            .body( "message", Matchers.equalTo( "CODE=631 not exists" ) );

    }

    @Test
    public void shouldThrowCategoryNotEmptyExceptionWhenDeleteCategory()
    {
        given()
            .contentType( ContentType.JSON )
            .when().delete( "/category/remove/10" )
            .then().log().everything()
            .assertThat()
            .statusCode( HttpStatus.SC_METHOD_NOT_ALLOWED ).and()
            .body( "message", Matchers.equalTo( "This category contains products" ) );

    }

    @Test
    public void shouldThrowCategoryNotFoundExceptionWhenUpdateCategory()
    {
        final String json = "{ \"id\": 30, \"code\":10, \"name\":\"Eletronicos\"}";
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
        final String json = "{ \"code\":11, \"name\":\"Lazer\"}";
        given()
            .contentType( ContentType.JSON )
            .body( json ).when().post( "/category/save" )
            .then().log().everything()
            .assertThat()
            .statusCode( HttpStatus.SC_METHOD_NOT_ALLOWED ).and()
            .body( "message", Matchers.equalTo( "CODE=11 already exists" ) );

    }

    @Test
    public void shouldThrowCategoryDuplicateNameExceptionWhenCreateProduct()
    {
        final String json = "{ \"code\":654, \"name\":\"Eletronicos\"}";
        given()
            .contentType( ContentType.JSON )
            .body( json ).when().post( "/category/save" )
            .then().log().everything()
            .assertThat()
            .statusCode( HttpStatus.SC_METHOD_NOT_ALLOWED ).and()
            .body( "message", Matchers.equalTo( "NAME=Eletronicos already exists" ) );

    }
}
