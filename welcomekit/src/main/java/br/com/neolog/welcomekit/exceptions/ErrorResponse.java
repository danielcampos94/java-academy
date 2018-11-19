package br.com.neolog.welcomekit.exceptions;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ErrorResponse
{

    private String dateTime;
    private List<String> message;

    public ErrorResponse()
    {
    }

    public ErrorResponse(
        final Date dateTime,
        final String message )
    {

        final SimpleDateFormat formatDate = new SimpleDateFormat( "dd/MM/yyyy HH:mm" );
        this.dateTime = formatDate.format( dateTime );
        this.message = Arrays.asList( message );
    }

    public ErrorResponse(
        final Date dateTime,
        final List<String> message )
    {

        final SimpleDateFormat formatDate = new SimpleDateFormat( "dd/MM/yyyy HH:mm" );
        this.dateTime = formatDate.format( dateTime );
        this.message = message;
    }

    public String getDateTime()
    {
        return dateTime;
    }

    public List<String> getMessage()
    {
        return message;
    }
}
