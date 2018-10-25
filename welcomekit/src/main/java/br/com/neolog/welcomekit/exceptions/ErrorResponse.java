package br.com.neolog.welcomekit.exceptions;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ErrorResponse
{

    private String dateTime;
    private List<String> message;
    private int codeError;
    private HttpStatus statusError;

    public ErrorResponse()
    {
    }

    public ErrorResponse(
        final Date dateTime,
        final String message,
        final int codeError,
        final HttpStatus statusError )
    {

        final SimpleDateFormat formatDate = new SimpleDateFormat( "dd/MM/yyyy HH:mm" );
        this.dateTime = formatDate.format( dateTime );
        this.message = Arrays.asList( message );
        this.codeError = codeError;
        this.statusError = statusError;
    }

    public ErrorResponse(
        final Date dateTime,
        final List<String> message,
        final int codeError,
        final HttpStatus statusError )
    {

        final SimpleDateFormat formatDate = new SimpleDateFormat( "dd/MM/yyyy HH:mm" );
        this.dateTime = formatDate.format( dateTime );
        this.message = message;
        this.codeError = codeError;
        this.statusError = statusError;
    }

    public String getDateTime()
    {
        return dateTime;
    }

    public List<String> getMessage()
    {
        return message;
    }

    public int getCodeError()
    {
        return codeError;
    }

    public HttpStatus getStatusError()
    {
        return statusError;
    }

}
