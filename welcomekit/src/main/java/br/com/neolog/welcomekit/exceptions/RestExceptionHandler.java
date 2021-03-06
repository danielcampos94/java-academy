package br.com.neolog.welcomekit.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.com.neolog.welcomekit.exceptions.customer.CustomerInvalidPasswordException;
import br.com.neolog.welcomekit.exceptions.customer.CustomerNotFoundException;
import br.com.neolog.welcomekit.exceptions.item.CartItemIllegalQuantityException;
import br.com.neolog.welcomekit.exceptions.product.ProductNotFoundException;

@ControllerAdvice
public class RestExceptionHandler
{

    @ExceptionHandler( MethodArgumentNotValidException.class )
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
        final MethodArgumentNotValidException exception,
        final WebRequest request )
    {
        final ErrorResponse errorResponse = new ErrorResponse( new Date(), bindingResultToString( exception ) );
        return new ResponseEntity<>( errorResponse, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( CartItemIllegalQuantityException.class )
    public ResponseEntity<ErrorResponse> handleCartItemIllegalQuantity(
        final CartItemIllegalQuantityException exception,
        final WebRequest request )
    {
        final ErrorResponse errorResponse = new ErrorResponse( new Date(), exception.getItem() );
        return new ResponseEntity<>( errorResponse, HttpStatus.METHOD_NOT_ALLOWED );
    }

    @ExceptionHandler( ProductNotFoundException.class )
    public ResponseEntity<ErrorResponse> handleProductNotFound(
        final ProductNotFoundException exception,
        final WebRequest request )
    {
        final ErrorResponse errorResponse = new ErrorResponse( new Date(), exception.getMessage() );
        return new ResponseEntity<>( errorResponse, HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler( CustomerInvalidPasswordException.class )
    public ResponseEntity<ErrorResponse> handleCustomerInvalidPassword(
        final CustomerInvalidPasswordException exception,
        final WebRequest request )
    {
        final ErrorResponse errorResponse = new ErrorResponse( new Date(), exception.getMessage() );
        return new ResponseEntity<>( errorResponse, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( CustomerNotFoundException.class )
    public ResponseEntity<ErrorResponse> handleCustomerNotFound(
        final CustomerNotFoundException exception,
        final WebRequest request )
    {
        final ErrorResponse errorResponse = new ErrorResponse( new Date(), exception.getMessage() );
        return new ResponseEntity<>( errorResponse, HttpStatus.NOT_FOUND );
    }

    private String bindingResultToString(
        final MethodArgumentNotValidException e )
    {

        final StringBuilder stringBuilder = new StringBuilder( "Validation failed. Error(s) details: " );
        for( final ObjectError error : e.getBindingResult().getAllErrors() ) {
            final String[] field = error.getCodes();
            stringBuilder.append( "[" ).append( field[ 0 ] ).append( ": " ).append( error.getDefaultMessage() ).append( "] " );
        }
        return stringBuilder.toString();
    }

}
