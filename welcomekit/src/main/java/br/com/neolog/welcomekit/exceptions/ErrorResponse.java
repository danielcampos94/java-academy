package br.com.neolog.welcomekit.exceptions;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;


public class ErrorResponse {

	private final String dateTime;
	private final String message;
	private final int codeError;
	private final HttpStatus statusError;
	
	
	public ErrorResponse(final Date dateTime, final String message, final int codeError, final HttpStatus statusError) {
		
		final SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		this.dateTime = formatDate.format(dateTime);
		this.message = message;
		this.codeError = codeError;
		this.statusError = statusError;
	}


	public String getDateTime() {
		return dateTime;
	}


	public String getMessage() {
		return message;
	}


	public int getCodeError() {
		return codeError;
	}


	public HttpStatus getStatusError() {
		return statusError;
	}


	
}
