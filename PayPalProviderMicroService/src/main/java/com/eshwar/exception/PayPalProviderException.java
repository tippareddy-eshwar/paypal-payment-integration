//PayPalProviderException .java(Custom Exception Class)
package com.eshwar.exception;
import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
public class PayPalProviderException extends RuntimeException
{

	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	private String errorMessage;
	private HttpStatus httpStatus;
	
	public PayPalProviderException(String errorCode, String errorMessage, HttpStatus httpStatus)
	{
		
		super(errorMessage);
		this.errorCode=errorCode;
		this.errorMessage=errorMessage;
		this.httpStatus=httpStatus;
	}

}
