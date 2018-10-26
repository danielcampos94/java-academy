package br.com.neolog.welcomekit;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.neolog.welcomekit.exceptions.ErrorResponse;
import br.com.neolog.welcomekit.models.Session;
import br.com.neolog.welcomekit.repository.SessionRepository;
import io.restassured.http.ContentType;

public class SessionIntegrationTest
    extends
        AbstractIntegrationTest
{
    @Autowired
    private SessionRepository sessionRepository;

    @Test
    public void loginShouldReturnStatusOKAndToken()
    {
        final String token = given().contentType( ContentType.JSON ).when()
            .post( "/session/login?email=session@teste.com.br&password=cart" ).then().log()
            .everything()
            .assertThat().statusCode( HttpStatus.SC_OK ).and().extract().response().asString();

        assertThat( token ).isNotNull();
    }

    @Test
    public void logoutShouldReturnStatusOkAndVerifyIfExpirationDateWasUpdatedForNow()
    {
        final Session session = sessionRepository.findByToken( "CHECKOUTCART2" );

        given().contentType( ContentType.JSON ).when()
            .post( "/session/logout?token=CHECKOUTCART2" ).then().log()
            .everything()
            .assertThat().statusCode( HttpStatus.SC_OK );

        final Session sessionAfterLogout = sessionRepository.findByToken( "CHECKOUTCART2" );

        assertThat( session.getExpirationDate() ).isGreaterThan( sessionAfterLogout.getExpirationDate() );
    }

    @Test
    public void loginShouldThrowCustomerNotFoundExceptionWhen()
    {
        final ErrorResponse response = given().contentType( ContentType.JSON ).when()
            .post( "/session/login?email=item@teste.com.br&password=cart" ).then().log()
            .everything()
            .assertThat().statusCode( HttpStatus.SC_NOT_FOUND ).and().extract().response().as( ErrorResponse.class );

        assertThat( response.getMessage() ).contains( "This customer not exists" );
    }

    @Test
    public void loginShouldThrowCustomerInvalidPasswordExceptionWhen()
    {
        final ErrorResponse response = given().contentType( ContentType.JSON ).when()
            .post( "/session/login?email=cart@teste.com.br&password=cartItem" ).then().log()
            .everything()
            .assertThat().statusCode( HttpStatus.SC_BAD_REQUEST ).and().extract().response().as( ErrorResponse.class );

        assertThat( response.getMessage() ).contains( "Invalid password" );
    }
}
